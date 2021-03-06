# GUI编程

## tkinter

### 顶层窗口：tkinter.TK()

### tkinter控件

|    控件     |                             描述                             |
| :---------: | :----------------------------------------------------------: |
|   Button    | 与Label类似，但提供额外功能，如鼠标悬浮、按下、释放以及键盘活动/事件 |
|   Canvas    | 提供绘制形状的功能（线段、椭圆、多边形、矩形），可以包含图像或位图 |
| Checkbutton |                一组选框，可以勾选其中的任意个                |
|    Entry    |                 单行文本框，用于收集键盘输入                 |
|    Frame    |                     包含其他控件的纯容器                     |
|    Label    |                      用于包含文本或图像                      |
| LabelFrame  |             标签和框架的组合，拥有额外的标签属性             |
|   Listbox   |               给用户显示一个选项列表来进行选择               |
|    Menu     |       按下Menubutton后弹出的选项列表，用户可以从中选择       |
| Menubutton  |                 用于包含菜单（下拉，级联等）                 |
|   Message   |            消息。与Label类似，不过可以显示成多行             |
| PanedWindow |           一个可以控制其他控件在其中摆放的容器控件           |
| Radiobutton |               一组按钮，其中只有一个可以“按下”               |
|    Scale    | 线性“滑块”控件，根据已设定的起始值和终止值，给出当前设定的精确值 |
|  Scrollbar  |    为Text、Canvas、Listbox、Enter等支持的控件提供滚动功能    |
|   Spinbox   |            Entry和Button的组合，允许对值进行调整             |
|    Text     |         多行文本框，用于收集（或显示）用户输入的文本         |
|  Toplevel   |          与Frame类似，不过它提供一个单独的窗口容器           |

### 实例

```python
import tkinter

root = tkinter.Tk()	#创建根布局
label = tkinter.Label(root,text='hello world') # 创建控件
label.pack() # 使用pack布局，向容器中添加组件，第一个组件在最上方，然后依次向下添加
tkinter.mainloop() # 进入事件（消息）循环，一旦检测到事件，就刷新组件

```





