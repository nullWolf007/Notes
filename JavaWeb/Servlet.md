# Servlet和HttpServlet和ServletContext

## Servlet的生命周期
1. Servlet创建
```text
默认第一次访问servlet是创建 调用init()方法
init(ServletConfig config)
ServletConfig:代表Servlet对象的配置信息
```
2. 每次访问都会执行的方法
```text
service(ServletRequest req, ServletResponse res);
ServletRequest:代表请求，认为ServletRequest内部封装的是http请求的信息
ServletResponse:代表响应，认为要封装的是响应的信息
```
3. Servlet销毁
```text
服务器关闭，Servlet就销毁了，调用destory()方法
```

## HttpServlet

## ServletContext对象
* ServletContext代表的是一个web应用的上下文对象，ServletContext对象内部封装的是该web应用的信息，一个web应用只有一个ServletContext对象
