# Ubuntu

## Linux常用命令
* 

## vi/vim常用命令
* 回到命令模式：esc
* 进入编辑模式：
  * i光标前插入
  * a光标后插入  
  * I光标行首插入 
  * A光标行末插入  
  * o光标下一行 
  * O光标上一行
* 进入末行模式：英文冒号:
* 复制：
  * yy复制光标当前行
  * 4yy复制光标所在行的四行
* 粘贴：p粘贴
* 删除/剪切：
  * x删除光标所在字符  
  * X删除光标所在前面的字符
  * dd删除光标所在行 
  * 2dd删除光标所在行的两行
  * D删除光标位置到行末的部分
  * d0删除光标所在位置到行首的部分
  * dw删除一个单词
* 撤销：u撤销刚刚的操作
* 反撤销：ctrl+r
* 移动光标
  * h左  
  * j下 
  * K上 
  * l右 
  * H当前屏幕的最顶端  
  * M当前屏幕的中间位置 
  * L当前屏幕的最下端 
  * ctrl+b上一页 
  * ctrl+f下一页  
  * ctrl+d上半页
  * ctrl+u下半页
  * 20G:快速定位20行代码
  * G:快速回到整个代码的最后一行
  * gg:快速回到整个代码的第一行
  * w向后跳一个单词的长度
  * b向前跳一个单词的长度
  * {跳到上一片代码
  * }跳到下一片代码
* 选中：v和V
* 移动：
  * &gt;&gt;向右移动代码
  * <<向左移动代码
* 重复上一次的命令：点号.
* 替换：
  * 全文：在末行模式中 %s/需要替换的字符串/替换的结果字符串/g
  * 部分：在末行模式中 1,10/需要替换的字符串/替换的结果字符串/g  第一行到第十行的部分
* 保存退出：ZZ(命令模式)
* 保存：w(末行模式)
* 退出：q(末行模式)
* 保存退出:wq(末行模式)
  
