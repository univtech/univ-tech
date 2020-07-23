# 包

## 标准库

 名称                                                                         | 概要
:-----------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 + [archive](https://golang.google.cn/pkg/archive/)                           | 
 + [archive tar](https://golang.google.cn/pkg/archive/tar/)                   | Package tar implements access to tar archives.
 + [archive zip](https://golang.google.cn/pkg/archive/zip/)                   | Package zip provides support for reading and writing ZIP archives.
 + [bufio](https://golang.google.cn/pkg/bufio/)                               | Package bufio implements buffered I/O. It wraps an io.Reader or io.Writer object, creating another object (Reader or Writer) that also implements the interface but provides buffering and some help for textual I/O.
 + [builtin](https://golang.google.cn/pkg/builtin/)                           | Package builtin provides documentation for Go's predeclared identifiers.
 + [bytes](https://golang.google.cn/pkg/bytes/)                               | Package bytes implements functions for the manipulation of byte slices.
 + [compress](https://golang.google.cn/pkg/compress/)                         | 
 + [compress bzip2](https://golang.google.cn/pkg/compress/bzip2/)             | Package bzip2 implements bzip2 decompression.
 + [compress flate](https://golang.google.cn/pkg/compress/flate/)             | Package flate implements the DEFLATE compressed data format, described in RFC 1951.
 + [compress gzip](https://golang.google.cn/pkg/compress/gzip/)               | Package gzip implements reading and writing of gzip format compressed files, as specified in RFC 1952.
 + [compress lzw](https://golang.google.cn/pkg/compress/lzw/)                 | Package lzw implements the Lempel-Ziv-Welch compressed data format, described in T. A. Welch, “A Technique for High-Performance Data Compression”, Computer, 17(6) (June 1984), pp 8-19.
 + [compress zlib](https://golang.google.cn/pkg/compress/zlib/)               | Package zlib implements reading and writing of zlib format compressed data, as specified in RFC 1950.
 + [container](https://golang.google.cn/pkg/container/)                       | 
 + [container heap](https://golang.google.cn/pkg/container/heap/)             | Package heap provides heap operations for any type that implements heap.Interface.
 + [container list](https://golang.google.cn/pkg/container/list/)             | Package list implements a doubly linked list.
 + [container ring](https://golang.google.cn/pkg/container/ring/)             | Package ring implements operations on circular lists.
 + [context](https://golang.google.cn/pkg/context/)                           | Package context defines the Context type, which carries deadlines, cancellation signals, and other request-scoped values across API boundaries and between processes.
 + [crypto](https://golang.google.cn/pkg/crypto/)                             | Package crypto collects common cryptographic constants.
 + [crypto aes](https://golang.google.cn/pkg/crypto/aes/)                     | Package aes implements AES encryption (formerly Rijndael), as defined in U.S. Federal Information Processing Standards Publication 197.
 + [crypto cipher](https://golang.google.cn/pkg/crypto/cipher/)               | Package cipher implements standard block cipher modes that can be wrapped around low-level block cipher implementations.
 + [crypto des](https://golang.google.cn/pkg/crypto/des/)                     | Package des implements the Data Encryption Standard (DES) and the Triple Data Encryption Algorithm (TDEA) as defined in U.S. Federal Information Processing Standards Publication 46-3.
 + [crypto dsa](https://golang.google.cn/pkg/crypto/dsa/)                     | Package dsa implements the Digital Signature Algorithm, as defined in FIPS 186-3.
 + [crypto ecdsa](https://golang.google.cn/pkg/crypto/ecdsa/)                 | Package ecdsa implements the Elliptic Curve Digital Signature Algorithm, as defined in FIPS 186-3.
 + [crypto ed25519](https://golang.google.cn/pkg/crypto/ed25519/)             | Package ed25519 implements the Ed25519 signature algorithm.
 + [crypto elliptic](https://golang.google.cn/pkg/crypto/elliptic/)           | Package elliptic implements several standard elliptic curves over prime fields.
 + [crypto hmac](https://golang.google.cn/pkg/crypto/hmac/)                   | Package hmac implements the Keyed-Hash Message Authentication Code (HMAC) as defined in U.S. Federal Information Processing Standards Publication 198.
 + [crypto md5](https://golang.google.cn/pkg/crypto/md5/)                     | Package md5 implements the MD5 hash algorithm as defined in RFC 1321.
 + [crypto rand](https://golang.google.cn/pkg/crypto/rand/)                   | Package rand implements a cryptographically secure random number generator.
 + [crypto rc4](https://golang.google.cn/pkg/crypto/rc4/)                     | Package rc4 implements RC4 encryption, as defined in Bruce Schneier's Applied Cryptography.
 + [crypto rsa](https://golang.google.cn/pkg/crypto/rsa/)                     | Package rsa implements RSA encryption as specified in PKCS#1.
 + [crypto sha1](https://golang.google.cn/pkg/crypto/sha1/)                   | Package sha1 implements the SHA-1 hash algorithm as defined in RFC 3174.
 + [crypto sha256](https://golang.google.cn/pkg/crypto/sha256/)               | Package sha256 implements the SHA224 and SHA256 hash algorithms as defined in FIPS 180-4.
 + [crypto sha512](https://golang.google.cn/pkg/crypto/sha512/)               | Package sha512 implements the SHA-384, SHA-512, SHA-512/224, and SHA-512/256 hash algorithms as defined in FIPS 180-4.
 + [crypto subtle](https://golang.google.cn/pkg/crypto/subtle/)               | Package subtle implements functions that are often useful in cryptographic code but require careful thought to use correctly.
 + [crypto tls](https://golang.google.cn/pkg/crypto/tls/)                     | Package tls partially implements TLS 1.2, as specified in RFC 5246, and TLS 1.3, as specified in RFC 8446.
 + [crypto x509](https://golang.google.cn/pkg/crypto/x509/)                   | Package x509 parses X.509-encoded keys and certificates.
 + [crypto x509 pkix](https://golang.google.cn/pkg/crypto/x509/pkix/)         | Package pkix contains shared, low level structures used for ASN.1 parsing and serialization of X.509 certificates, CRL and OCSP.
 + [database](https://golang.google.cn/pkg/database/)                         | 
 + [database sql](https://golang.google.cn/pkg/database/sql/)                 | Package sql provides a generic interface around SQL (or SQL-like) databases.
 + [database sql driver](https://golang.google.cn/pkg/database/sql/driver/)   | Package driver defines interfaces to be implemented by database drivers as used by package sql.
 + [debug](https://golang.google.cn/pkg/debug/)                               | 
 + [debug dwarf](https://golang.google.cn/pkg/debug/dwarf/)                   | Package dwarf provides access to DWARF debugging information loaded from executable files, as defined in the DWARF 2.0 Standard at http://dwarfstd.org/doc/dwarf-2.0.0.pdf
 + [debug elf](https://golang.google.cn/pkg/debug/elf/)                       | Package elf implements access to ELF object files.
 + [debug gosym](https://golang.google.cn/pkg/debug/gosym/)                   | Package gosym implements access to the Go symbol and line number tables embedded in Go binaries generated by the gc compilers.
 + [debug macho](https://golang.google.cn/pkg/debug/macho/)                   | Package macho implements access to Mach-O object files.
 + [debug pe](https://golang.google.cn/pkg/debug/pe/)                         | Package pe implements access to PE (Microsoft Windows Portable Executable) files.
 + [debug plan9obj](https://golang.google.cn/pkg/debug/plan9obj/)             | Package plan9obj implements access to Plan 9 a.out object files.
 + [encoding](https://golang.google.cn/pkg/encoding/)                         | Package encoding defines interfaces shared by other packages that convert data to and from byte-level and textual representations.
 + [encoding ascii85](https://golang.google.cn/pkg/encoding/ascii85/)         | Package ascii85 implements the ascii85 data encoding as used in the btoa tool and Adobe's PostScript and PDF document formats.
 + [encoding asn1](https://golang.google.cn/pkg/encoding/asn1/)               | Package asn1 implements parsing of DER-encoded ASN.1 data structures, as defined in ITU-T Rec X.690.
 + [encoding base32](https://golang.google.cn/pkg/encoding/base32/)           | Package base32 implements base32 encoding as specified by RFC 4648.
 + [encoding base64](https://golang.google.cn/pkg/encoding/base64/)           | Package base64 implements base64 encoding as specified by RFC 4648.
 + [encoding binary](https://golang.google.cn/pkg/encoding/binary/)           | Package binary implements simple translation between numbers and byte sequences and encoding and decoding of varints.
 + [encoding csv](https://golang.google.cn/pkg/encoding/csv/)                 | Package csv reads and writes comma-separated values (CSV) files.
 + [encoding gob](https://golang.google.cn/pkg/encoding/gob/)                 | Package gob manages streams of gobs - binary values exchanged between an Encoder (transmitter) and a Decoder (receiver).
 + [encoding hex](https://golang.google.cn/pkg/encoding/hex/)                 | Package hex implements hexadecimal encoding and decoding.
 + [encoding json](https://golang.google.cn/pkg/encoding/json/)               | Package json implements encoding and decoding of JSON as defined in RFC 7159.
 + [encoding pem](https://golang.google.cn/pkg/encoding/pem/)                 | Package pem implements the PEM data encoding, which originated in Privacy Enhanced Mail.
 + [encoding xml](https://golang.google.cn/pkg/encoding/xml/)                 | Package xml implements a simple XML 1.0 parser that understands XML name spaces.
 + [errors](https://golang.google.cn/pkg/errors/)                             | Package errors implements functions to manipulate errors.
 + [expvar](https://golang.google.cn/pkg/expvar/)                             | Package expvar provides a standardized interface to public variables, such as operation counters in servers.
 + [flag](https://golang.google.cn/pkg/flag/)                                 | Package flag implements command-line flag parsing.
 + [fmt](https://golang.google.cn/pkg/fmt/)                                   | Package fmt implements formatted I/O with functions analogous to C's printf and scanf.
 + [go](https://golang.google.cn/pkg/go/)                                     | 
 + [go ast](https://golang.google.cn/pkg/go/ast/)                             | Package ast declares the types used to represent syntax trees for Go packages.
 + [go build](https://golang.google.cn/pkg/go/build/)                         | Package build gathers information about Go packages.
 + [go constant](https://golang.google.cn/pkg/go/constant/)                   | Package constant implements Values representing untyped Go constants and their corresponding operations.
 + [go doc](https://golang.google.cn/pkg/go/doc/)                             | Package doc extracts source code documentation from a Go AST.
 + [go format](https://golang.google.cn/pkg/go/format/)                       | Package format implements standard formatting of Go source.
 + [go importer](https://golang.google.cn/pkg/go/importer/)                   | Package importer provides access to export data importers.
 + [go parser](https://golang.google.cn/pkg/go/parser/)                       | Package parser implements a parser for Go source files.
 + [go printer](https://golang.google.cn/pkg/go/printer/)                     | Package printer implements printing of AST nodes.
 + [go scanner](https://golang.google.cn/pkg/go/scanner/)                     | Package scanner implements a scanner for Go source text.
 + [go token](https://golang.google.cn/pkg/go/token/)                         | Package token defines constants representing the lexical tokens of the Go programming language and basic operations on tokens (printing, predicates).
 + [go types](https://golang.google.cn/pkg/go/types/)                         | Package types declares the data types and implements the algorithms for type-checking of Go packages.
 + [hash](https://golang.google.cn/pkg/hash/)                                 | Package hash provides interfaces for hash functions.
 + [hash adler32](https://golang.google.cn/pkg/hash/adler32/)                 | Package adler32 implements the Adler-32 checksum.
 + [hash crc32](https://golang.google.cn/pkg/hash/crc32/)                     | Package crc32 implements the 32-bit cyclic redundancy check, or CRC-32, checksum.
 + [hash crc64](https://golang.google.cn/pkg/hash/crc64/)                     | Package crc64 implements the 64-bit cyclic redundancy check, or CRC-64, checksum.
 + [hash fnv](https://golang.google.cn/pkg/hash/fnv/)                         | Package fnv implements FNV-1 and FNV-1a, non-cryptographic hash functions created by Glenn Fowler, Landon Curt Noll, and Phong Vo.
 + [hash maphash](https://golang.google.cn/pkg/hash/maphash/)                 | Package maphash provides hash functions on byte sequences.
 + [html](https://golang.google.cn/pkg/html/)                                 | Package html provides functions for escaping and unescaping HTML text.
 + [html template](https://golang.google.cn/pkg/html/template/)               | Package template (html/template) implements data-driven templates for generating HTML output safe against code injection.
 + [image](https://golang.google.cn/pkg/image/)                               | Package image implements a basic 2-D image library.
 + [image color](https://golang.google.cn/pkg/image/color/)                   | Package color implements a basic color library.
 + [image color palette](https://golang.google.cn/pkg/image/color/palette/)   | Package palette provides standard color palettes.
 + [image draw](https://golang.google.cn/pkg/image/draw/)                     | Package draw provides image composition functions.
 + [image gif](https://golang.google.cn/pkg/image/gif/)                       | Package gif implements a GIF image decoder and encoder.
 + [image jpeg](https://golang.google.cn/pkg/image/jpeg/)                     | Package jpeg implements a JPEG image decoder and encoder.
 + [image png](https://golang.google.cn/pkg/image/png/)                       | Package png implements a PNG image decoder and encoder.
 + [index](https://golang.google.cn/pkg/index/)                               | 
 + [index suffixarray](https://golang.google.cn/pkg/index/suffixarray/)       | Package suffixarray implements substring search in logarithmic time using an in-memory suffix array.
 + [io](https://golang.google.cn/pkg/io/)                                     | Package io provides basic interfaces to I/O primitives.
 + [io ioutil](https://golang.google.cn/pkg/io/ioutil/)                       | Package ioutil implements some I/O utility functions.
 + [log](https://golang.google.cn/pkg/log/)                                   | Package log implements a simple logging package.
 + [log syslog](https://golang.google.cn/pkg/log/syslog/)                     | Package syslog provides a simple interface to the system log service.
 + [math](https://golang.google.cn/pkg/math/)                                 | Package math provides basic constants and mathematical functions.
 + [math big](https://golang.google.cn/pkg/math/big/)                         | Package big implements arbitrary-precision arithmetic (big numbers).
 + [math bits](https://golang.google.cn/pkg/math/bits/)                       | Package bits implements bit counting and manipulation functions for the predeclared unsigned integer types.
 + [math cmplx](https://golang.google.cn/pkg/math/cmplx/)                     | Package cmplx provides basic constants and mathematical functions for complex numbers.
 + [math rand](https://golang.google.cn/pkg/math/rand/)                       | Package rand implements pseudo-random number generators.
 + [mime](https://golang.google.cn/pkg/mime/)                                 | Package mime implements parts of the MIME spec.
 + [mime multipart](https://golang.google.cn/pkg/mime/multipart/)             | Package multipart implements MIME multipart parsing, as defined in RFC 2046.
 + [mime quotedprintable](https://golang.google.cn/pkg/mime/quotedprintable/) | Package quotedprintable implements quoted-printable encoding as specified by RFC 2045.
 + [net](https://golang.google.cn/pkg/net/)                                   | Package net provides a portable interface for network I/O, including TCP/IP, UDP, domain name resolution, and Unix domain sockets.
 + [net http](https://golang.google.cn/pkg/net/http/)                         | Package http provides HTTP client and server implementations.
 + [net http cgi](https://golang.google.cn/pkg/net/http/cgi/)                 | Package cgi implements CGI (Common Gateway Interface) as specified in RFC 3875.
 + [net http cookiejar](https://golang.google.cn/pkg/net/http/cookiejar/)     | Package cookiejar implements an in-memory RFC 6265-compliant http.CookieJar.
 + [net http fcgi](https://golang.google.cn/pkg/net/http/fcgi/)               | Package fcgi implements the FastCGI protocol.
 + [net http httptest](https://golang.google.cn/pkg/net/http/httptest/)       | Package httptest provides utilities for HTTP testing.
 + [net http httptrace](https://golang.google.cn/pkg/net/http/httptrace/)     | Package httptrace provides mechanisms to trace the events within HTTP client requests.
 + [net http httputil](https://golang.google.cn/pkg/net/http/httputil/)       | Package httputil provides HTTP utility functions, complementing the more common ones in the net/http package.
 + [net http pprof](https://golang.google.cn/pkg/net/http/pprof/)             | Package pprof serves via its HTTP server runtime profiling data in the format expected by the pprof visualization tool.
 + [net mail](https://golang.google.cn/pkg/net/mail/)                         | Package mail implements parsing of mail messages.
 + [net rpc](https://golang.google.cn/pkg/net/rpc/)                           | Package rpc provides access to the exported methods of an object across a network or other I/O connection.
 + [net rpc jsonrpc](https://golang.google.cn/pkg/net/rpc/jsonrpc/)           | Package jsonrpc implements a JSON-RPC 1.0 ClientCodec and ServerCodec for the rpc package.
 + [net smtp](https://golang.google.cn/pkg/net/smtp/)                         | Package smtp implements the Simple Mail Transfer Protocol as defined in RFC 5321.
 + [net textproto](https://golang.google.cn/pkg/net/textproto/)               | Package textproto implements generic support for text-based request/response protocols in the style of HTTP, NNTP, and SMTP.
 + [net url](https://golang.google.cn/pkg/net/url/)                           | Package url parses URLs and implements query escaping.
 + [os](https://golang.google.cn/pkg/os/)                                     | Package os provides a platform-independent interface to operating system functionality.
 + [os exec](https://golang.google.cn/pkg/os/exec/)                           | Package exec runs external commands.
 + [os signal](https://golang.google.cn/pkg/os/signal/)                       | Package signal implements access to incoming signals.
 + [os user](https://golang.google.cn/pkg/os/user/)                           | Package user allows user account lookups by name or id.
 + [path](https://golang.google.cn/pkg/path/)                                 | Package path implements utility routines for manipulating slash-separated paths.
 + [path filepath](https://golang.google.cn/pkg/path/filepath/)               | Package filepath implements utility routines for manipulating filename paths in a way compatible with the target operating system-defined file paths.
 + [plugin](https://golang.google.cn/pkg/plugin/)                             | Package plugin implements loading and symbol resolution of Go plugins.
 + [reflect](https://golang.google.cn/pkg/reflect/)                           | Package reflect implements run-time reflection, allowing a program to manipulate objects with arbitrary types.
 + [regexp](https://golang.google.cn/pkg/regexp/)                             | Package regexp implements regular expression search.
 + [regexp syntax](https://golang.google.cn/pkg/regexp/syntax/)               | Package syntax parses regular expressions into parse trees and compiles parse trees into programs.
 + [runtime](https://golang.google.cn/pkg/runtime/)                           | Package runtime contains operations that interact with Go's runtime system, such as functions to control goroutines.
 + [runtime cgo](https://golang.google.cn/pkg/runtime/cgo/)                   | Package cgo contains runtime support for code generated by the cgo tool.
 + [runtime debug](https://golang.google.cn/pkg/runtime/debug/)               | Package debug contains facilities for programs to debug themselves while they are running.
 + [runtime msan](https://golang.google.cn/pkg/runtime/msan/)                 | 
 + [runtime pprof](https://golang.google.cn/pkg/runtime/pprof/)               | Package pprof writes runtime profiling data in the format expected by the pprof visualization tool.
 + [runtime race](https://golang.google.cn/pkg/runtime/race/)                 | Package race implements data race detection logic.
 + [runtime trace](https://golang.google.cn/pkg/runtime/trace/)               | Package trace contains facilities for programs to generate traces for the Go execution tracer.
 + [sort](https://golang.google.cn/pkg/sort/)                                 | Package sort provides primitives for sorting slices and user-defined collections.
 + [strconv](https://golang.google.cn/pkg/strconv/)                           | Package strconv implements conversions to and from string representations of basic data types.
 + [strings](https://golang.google.cn/pkg/strings/)                           | Package strings implements simple functions to manipulate UTF-8 encoded strings.
 + [sync](https://golang.google.cn/pkg/sync/)                                 | Package sync provides basic synchronization primitives such as mutual exclusion locks.
 + [sync atomic](https://golang.google.cn/pkg/sync/atomic/)                   | Package atomic provides low-level atomic memory primitives useful for implementing synchronization algorithms.
 + [syscall](https://golang.google.cn/pkg/syscall/)                           | Package syscall contains an interface to the low-level operating system primitives.
 + [syscall js](https://golang.google.cn/pkg/syscall/js/)                     | Package js gives access to the WebAssembly host environment when using the js/wasm architecture.
 + [testing](https://golang.google.cn/pkg/testing/)                           | Package testing provides support for automated testing of Go packages.
 + [testing iotest](https://golang.google.cn/pkg/testing/iotest/)             | Package iotest implements Readers and Writers useful mainly for testing.
 + [testing quick](https://golang.google.cn/pkg/testing/quick/)               | Package quick implements utility functions to help with black box testing.
 + [text](https://golang.google.cn/pkg/text/)                                 | 
 + [text scanner](https://golang.google.cn/pkg/text/scanner/)                 | Package scanner provides a scanner and tokenizer for UTF-8-encoded text.
 + [text tabwriter](https://golang.google.cn/pkg/text/tabwriter/)             | Package tabwriter implements a write filter (tabwriter.Writer) that translates tabbed columns in input into properly aligned text.
 + [text template](https://golang.google.cn/pkg/text/template/)               | Package template implements data-driven templates for generating textual output.
 + [text template parse](https://golang.google.cn/pkg/text/template/parse/)   | Package parse builds parse trees for templates as defined by text/template and html/template.
 + [time](https://golang.google.cn/pkg/time/)                                 | Package time provides functionality for measuring and displaying time.
 + [unicode](https://golang.google.cn/pkg/unicode/)                           | Package unicode provides data and functions to test some properties of Unicode code points.
 + [unicode utf16](https://golang.google.cn/pkg/unicode/utf16/)               | Package utf16 implements encoding and decoding of UTF-16 sequences.
 + [unicode utf8](https://golang.google.cn/pkg/unicode/utf8/)                 | Package utf8 implements functions and constants to support text encoded in UTF-8.
 + [unsafe](https://golang.google.cn/pkg/unsafe/)                             | Package unsafe contains operations that step around the type safety of Go programs.

## 其他包

### 子仓库

These packages are part of the Go Project but outside the main Go tree.

They are developed under looser [compatibility requirements](https://golang.google.cn/doc/go1compat) than the Go core.

Install them with "[go get](https://golang.google.cn/cmd/go/#hdr-Download_and_install_packages_and_dependencies)".

 名称                                                      | 概要
:----------------------------------------------------------|:-------------------------------------------------------------------------------------
 + [benchmarks](https://godoc.org/golang.org/x/benchmarks) | benchmarks to measure Go as it is developed.
 + [blog](https://godoc.org/golang.org/x/blog)             | [blog.golang.org](https://blog.golang.org/)'s implementation.
 + [build](https://godoc.org/golang.org/x/build)           | [build.golang.org](https://build.golang.org/)'s implementation.
 + [crypto](https://godoc.org/golang.org/x/crypto)         | additional cryptography packages.
 + [debug](https://godoc.org/golang.org/x/debug)           | an experimental debugger for Go.
 + [image](https://godoc.org/golang.org/x/image)           | additional imaging packages.
 + [mobile](https://godoc.org/golang.org/x/mobile)         | experimental support for Go on mobile platforms.
 + [net](https://godoc.org/golang.org/x/net)               | additional networking packages.
 + [perf](https://godoc.org/golang.org/x/perf)             | packages and tools for performance measurement, storage, and analysis.
 + [pkgsite](https://pkg.go.dev/golang.org/x/pkgsite)      | home of the pkg.go.dev website.
 + [review](https://godoc.org/golang.org/x/review)         | a tool for working with Gerrit code reviews.
 + [sync](https://godoc.org/golang.org/x/sync)             | additional concurrency primitives.
 + [sys](https://godoc.org/golang.org/x/sys)               | packages for making system calls.
 + [text](https://godoc.org/golang.org/x/text)             | packages for working with text.
 + [time](https://godoc.org/golang.org/x/time)             | additional time packages.
 + [tools](https://godoc.org/golang.org/x/tools)           | godoc, goimports, gorename, and other tools.
 + [tour](https://godoc.org/golang.org/x/tour)             | [tour.golang.org](https://tour.golang.org/)'s implementation.
 + [exp](https://godoc.org/golang.org/x/exp)               | experimental and deprecated packages (handle with care; may change without warning).

### 社区

These services can help you find Open Source packages provided by the community.

 名称                                                                | 概要
:--------------------------------------------------------------------|:-----------------------------------
 + [GoDoc](https://godoc.org/)                                       | a package index and search engine.
 + [Go Search](http://go-search.org/)                                | a code search engine.
 + [Projects at the Go Wiki](https://golang.google.cn/wiki/Projects) | a curated list of Go projects.




