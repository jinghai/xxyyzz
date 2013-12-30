package com.ipet.android.service;

import org.springframework.security.crypto.encrypt.AndroidEncryptors;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.DuplicateConnectionException;
import org.springframework.social.connect.sqlite.SQLiteConnectionRepository;
import org.springframework.social.connect.sqlite.support.SQLiteConnectionRepositoryHelper;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import android.content.Context;
import com.ipet.android.MyApp;
import com.ipet.android.sdk.api.connect.IpetConnectionFactory;

public abstract class SocialService {

    private static ConnectionFactoryRegistry connectionFactoryRegistry;
    private static SQLiteConnectionRepository connectionRepository;

    public static void init(Context context) {
        connectionFactoryRegistry = new ConnectionFactoryRegistry();
        connectionFactoryRegistry.addConnectionFactory(new IpetConnectionFactory(MyApp.APP_ID, MyApp.APP_SECRET));

        SQLiteConnectionRepositoryHelper repositoryHelper = new SQLiteConnectionRepositoryHelper(context);
        connectionRepository = new SQLiteConnectionRepository(repositoryHelper,
                connectionFactoryRegistry, AndroidEncryptors.text("password", "5c0744940b5c369b"));
    }

    public static <E> void adicionarConexao(Connection<E> connection) {
        try {
            connectionRepository.addConnection(connection);
        } catch (DuplicateConnectionException e) {
            // connection already exists in repository!
        }
    }

    public static <E> E getApi(Class<E> apiType) {
        return connectionRepository.findPrimaryConnection(apiType).getApi();
    }

    public static <E> ConnectionFactory<E> getConnectionFactory(Class<E> apiType) {
        return (ConnectionFactory<E>) connectionFactoryRegistry.getConnectionFactory(apiType);
    }

}
