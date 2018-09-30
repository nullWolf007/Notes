# Web客户端和服务器

## 前言

- 安全套接字层（Secure SocketLayerr，SSL）：用来创建一个套接字，加密通过该套接字传输的数据

------

## 代理服务器

- 可能与防火墙一同工作，通过代理服务器，网络管理员可以只让一部分计算机访问网络，可以更好的监控网络的数据传输；可以缓存数据

### 正向代理

- 处理缓存数据

### 反向代理

- 作为防火墙或加密数据

------

## Python Web客户端

### 统一资源定位符（URL）

### 统一资源标识符（URI）

- URL是URI的一部分，现在几乎只使用属于URL的URI

- URL格式 

  ```text
  prot_sch://net_loc/path;params?query#frag
  ```

  | URL组件  |                 描述                 |
  | :------: | :----------------------------------: |
  | prot_sch |          网络协议或下载方案          |
  | net_loc  |   服务器所在地（也许含有用户信息）   |
  |   path   | 使用斜杠(/)分割的文件或CGI应用的路径 |
  |  params  |               可选参数               |
  |  query   |     连接符(&)分割的一系列键值对      |
  |   frag   |        指定文档内特定锚的部分        |

  - net_loc

    ```text
    user:passwd@host:port
    ```

    |  组件  |                   描述                    |
    | :----: | :---------------------------------------: |
    |  user  |               用户名或登陆                |
    | passwd |                 用户密码                  |
    |  host  | 运行Web服务器的计算机名称或地址（必需的） |
    |  port  |        端口号（如果不是默认的80）         |

    - 用户名和密码只会在FTP连接的时候使用，即便使用FTP，大多数连接都是匿名的，不需要用户名和密码

### urllib模块/包

- 用于从指定URL下载数据，同时也可以对字符串进行编码、解码工作，以便在URL中以正确的形式显示出来

|                             函数                             |                             描述                             |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
|       ulib.request.urlopen(urlstr,postQueryData=None)        |   打开一个给定URL字符串表示的Web连接，并返回文件类型的对象   |
|          urlopen()文件类型对象方法（假设f为其对象）          |                                                              |
|                       f.read([bytes])                        |                  从f中读出所有或bytes个字节                  |
|                         f.readline()                         |                        从f中读取一行                         |
|                        f.readlines()                         |                从f中读出所有行，作为列表返回                 |
|                          f.close()                           |                        关闭f的URL连接                        |
|                          f.fileno()                          |                       返回f的文件句柄                        |
|                           f.info()                           |                      获得f的MIME头文件                       |
|                          f.geturl()                          |                        返回f的真正URL                        |
|             ------------------------------------             |             ------------------------------------             |
| urllib.request.urlretrieve (url,filename=None,reporthook=None,data=None) | 下载指定URL对应的完整的HTML，另存为文件；返回一个二元组(fielname,mime_hdrs),filename是含有下载数据的本地文件名，mime_hdrs是Web服务器响应后返回的一系列MIME文件头 |
|             ------------------------------------             |             ------------------------------------             |
|            urllib.request.quote(urldata,safe='/')            |   获取URL数据，并将其编码，使其可以用于URL字符串中（转换）   |
|               urllib.request.unquote(urldata)                | 将所有编码为“%xx”式字符转换为等价的ASCII码值（与quote相反）  |
|             ------------------------------------             |             ------------------------------------             |
|                 urllib.parse.urlencode(dict)                 | 将dict的键值对通过quote_plus()编译成有效的CGI查询字符串，即用quite_plus()对这个字符串进行编码 |

- urllib 侧重于 url 基本的请求构造，urllib2侧重于 http 协议请求的处理，而 urllib3是服务于升级的http 1.1标准，且拥有高效 http连接池管理及 http 代理服务的功能库

