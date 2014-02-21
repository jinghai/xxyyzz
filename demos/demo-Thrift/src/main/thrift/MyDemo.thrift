/**
 * The first thing to know about are types. The available types in Thrift are:
 *
 *  bool        Boolean, one byte
 *  byte        Signed byte
 *  i16         Signed 16-bit integer
 *  i32         Signed 32-bit integer
 *  i64         Signed 64-bit integer
 *  double      64-bit floating point value
 *  string      String
 *  binary      Blob (byte array)
 *  map<t1,t2>  Map from one type to another
 *  list<t1>    Ordered list of one type
 *  set<t1>     Set of unique elements of one type
 *
 * Did you also notice that Thrift supports C style comments?
 */

namespace cpp gen.my
namespace java gen.my


enum RetCode {
    Success = 0,
    UnknowError,
    Failed
}


struct Response {
    1:RetCode ret_code,
    2:string err_msg
}


service MyDemo  {
    string getText(),
    binary getFile(1:string file_name),
    Response setFile(1:string file_name, 3:binary write_buffer, 4:i32 length),
    Response setText(1:string text)
}

