# Microsoft Office编程

## COM

- COM是Component Object Model(组件对象模型)，是DOS可执行命令文件

## Python与COM客户端编程

### 步骤

1. 启用应用
2. 添加合适的文档以工作（或载入一个已经存在的文档）
3. 使应用可见
4. 执行文档所需的所有工作
5. 保存或放弃文档
6. 退出

## Excel

### 实例

```python
import tkinter
from tkinter import messagebox
from time import sleep
import win32com.client as win32

warn = lambda app: messagebox.showwarning(app, 'exit?')
RANGE = range(3, 8)


def excel():
    app = 'Excel'
    x1 = win32.gencache.EnsureDispatch('%s.Application' % app)
    ss = x1.Workbooks.Add()
    sh = ss.ActiveSheet
    x1.Visible = True # 使其excel可见
    sleep(1)

    sh.Cells(1, 1).Value = 'Python-to-%s Demo' % app # 按照坐标进行写入数据
    sleep(1)
    for i in RANGE:
        sh.Cells(i, 1).Value = 'Line %d' % i
        sleep(1)
    sh.Cells(i + 2, 1).Value = "tH-TH-TH-THAT's all folks!"

    warn(app)

    ss.Close(False) # False表示退出不保存  True会提示进行保存
    x1.Application.Quit()


if __name__ == '__main__':
    tkinter.Tk().withdraw()
    excel()

```

## Word

### 实例

```python
import tkinter
from tkinter import messagebox
from time import sleep
import win32com.client as win32

warn = lambda app: messagebox.showwarning(app, 'exit?')
RANGE = range(3, 8)


def word():
    app = 'Word'
    word = win32.gencache.EnsureDispatch('%s.Application' % app)
    doc = word.Documents.Add()
    word.Visible = True
    sleep(1)

    rng = doc.Range(0, 0)
    rng.InsertAfter('Python-to-%s Test\r\n\r\n' % app)
    sleep(1)
    for i in RANGE:
        rng.InsertAfter('Line %d\r\n' % i)
        sleep(1)
    rng.InsertAfter("\r\ntH-TH-TH-THAT's all folks!\r\n")

    warn(app)
    doc.Close(False)
    word.Application.Quit()


if __name__ == '__main__':
    tkinter.Tk().withdraw()
    word()
```

## PowerPoint

### 实例(不能正常运行)

```python
import tkinter
from tkinter import messagebox
from time import sleep
import win32com.client as win32

warn = lambda app: messagebox.showwarning(app, 'exit?')
RANGE = range(3, 8)


def ppoint():
    app = 'PowerPoint'
    point = win32.gencache.EnsureDispatch('%s.Application' % app)
    pres = point.Presentations.Add()
    point.Visible = True
    s1 = pres.Slides.Add(1, win32.constants.ppLayoutText)
    sleep(1)

    sla = s1.Shapes[0].TextFrame.TextRange
    sla.Text = 'Python-to_%s Demo' % app
    sleep(1)
    slb = s1.Shapes[1].TextFrame.TextRange
    for i in RANGE:
        slb.InsertAfter("Line %d\r\n" % i)
        sleep(1)
    slb.InsertAfter("\r\nTh-th-th-that's all folks!")

    warn(app)
    pres.Close(False)
    point.Quit()


if __name__ == '__main__':
    tkinter.Tk().withdraw()
    ppoint()

```

