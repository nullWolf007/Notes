# Django框架

## 前言

### CSRF

- Django 有数据保留特性。这不允许不安全的 POST 通过跨站点请求伪造（Cross-Site Request Forgery，CSRF）来进行攻击
- 解决办法：添加CSRF标记 {% csrf_token %};添加RequestContext实例

## 步骤

用户：123456
密码：123456

1. 新建Django项目

   ```python
   django-admin startproject projectName
   ```

- Django项目文件

  |     文件名      |        描述/用途         |
  | :-------------: | :----------------------: |
  | \_\_init\_\_.py | 告诉Python这是一个软件包 |
  |     urls.py     | 全局URL配置（“URLconf”） |
  |   settings.py   |      项目相关的配置      |
  |    manage.py    |     应用的命令行接口     |

1. 运行Django内置的Web服务器

   ```python
   # 找到项目中拥有manage.py文件夹，运行如下语句
   python manage.py runserver  
   # 指定端口号
   python manage.py runserver 8080
   ```

2. 项目中创建应用

   ```python
   python manage.py startapp appName
   ```

- 创建的应用的文件

  |     文件名      |                          描述/目的                           |
  | :-------------: | :----------------------------------------------------------: |
  | \_\_init\_\_.py |                     告诉Python这是一个包                     |
  |     urls.py     | 应用的URL配置文件（“URLconf”），这个文件并不像项目的URLconf那样自动创建 |
  |    models.py    |                           数据模型                           |
  |    views.py     |                 视图函数（即MVC中的控制器）                  |
  |    tests.py     |                           单元测试                           |

1. 让Django知道这个应用

   ```python
   # 在项目中的setting.py中INSTALLED_APPS中添加新的应用
   INSTALLED_APPS = (
       '...',
       'blog',
   )
   
   ```

2. 创建数据模型创建数据表

   ```python
   # 在models.py文件中创建数据模型，继承父类Model
   class BolgPost(models.Model):
       title = models.CharField(max_length=150)
       body = models.TextField()
       timestamp = models.DateTimeField()
   ```

3. 设置数据库

   ```python
   # sqlite 默认即可
   DATABASES = {
       'default': {
           'ENGINE': 'django.db.backends.sqlite3',
           'NAME': os.path.join(BASE_DIR, 'db.sqlite3'),
       }
   }
   # mysql
   DATABASES = {
       'default': {
           'ENGINE': 'django.db.backends.mysql',
           'NAME': 'testdb',
           'USER': 'username',
           'PASSWORD': 'psw',
           'HOST': '',
           'PORT': '',
       }
   }
   ```

4. 创建表

   ```python
   python manage.py syncdb
   ```

5. Django管理应用

   ```python
   # 1.创建表
   python manage.py syncdb
   # 2.在应用的admin.py中注册数据模型类
   from . import models
   admin.site.register(models.BlogPost)
   # 3.访问http://localhost:8000/admin这个URL，可以查看数据
   ```

6. 创建用户界面

   - 组件
     - 一个模板：显示通过Python类字典对象传入的信息
     - 一个视图函数：用于执行针对请求的核心逻辑。视图会从数据库获取信息，并格式化显示结果
     - 一个URL模式：将传入的请求映射到对应的视图中，同时也可以将参数传递给视图

   ```python
   # 1.在项目的urls.py中指定URL。app代表应用名
   urlpatterns = [
       url(r'^admin/', include(admin.site.urls)),
       url(r'^app/', include('app.urls')),
   ]
   # 2.在应用中新建urls.py,并添加视图的URL。def代表view.py中的函数
   from . import views
   urlpatterns = [
       url(r'^$', views.def),
   ]
   # 3.在view.py中实现函数，传递数据到templates中的html页面
   # 4.新建templates，并在其文件夹中创建html页面
   ```

7. view.py中的数据（从数据库中得到）

   ```python
   # Objects属性式模型的Manager类，其中all()方法来获取QuerySet,可以间接获取到所有数据
   posts = models.BlogPost.objects.all()
   t = loader.get_template('home.html')
   c = Context({'posts': posts})
   return HttpResponse(t.render(c))
   ```

   p430
