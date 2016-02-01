## BoilingLoadingView

 - 作者：Yellow5A5
 
###简介
---
&emsp;&emsp;这是一个锅煮萝卜的Loading动画，效果仿照自之前IOS上看到的一个效果，觉得挺有意思，就搬过来了－。－

&emsp;&emsp;在此做成了Dialog的样式，方便作为LoadingView去使用。

###效果图
---
![image](https://github.com/Yellow5A5/BoilingLoadingView/blob/master/Demo1.gif)

![image](https://github.com/Yellow5A5/BoilingLoadingView/blob/master/Demo2.gif)

###结构
---
![image](https://github.com/Yellow5A5/BoilingLoadingView/blob/master/StructureShow.png)

###引入调用
---
&emsp;&emsp;动画分别两个阶段：

 - 1、各种蔬菜进入，锅盖盖上
 - 2、开始加水煮菜喷锅

BoilingDialog（Loading）参考：
```
                BoilingDialog.Builder builder = new BoilingDialog.Builder(MainActivity.this);
                final BoilingDialog dialog = builder.build();
                dialog.show();
```


&emsp;&emsp;不使用Dialog的情况,直接调用BoilingPanView的beginFirstInAnim方法（第一个动画）：
```
        buttonInit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mBoilingPanView.beginFirstInAnim();
            }
        });
```

&emsp;&emsp;在第一个动画结束时自动执行第二个动画（beginBoilingAnim）
```
            @Override
            public void onAnimationEnd(Animation animation) {
                if (mBoilingAnimListener != null) {
                    //这里是为了给外部留有操作的空间
                    mBoilingAnimListener.onFirstAnimEnd();
                } else {
                    beginBoilingAnim();
                }
            }
```
&emsp;&emsp;可以看到我在第一个动画结束加入了回调，通过实现回调可以由使用者自己去决定第二个动画播放的时机。
```
    public interface BoilingAnimListener {
        //初始动画结束监听
        void onFirstAnimEnd();
    }

    public void setBoilingAnimListener(BoilingAnimListener l) {
        this.mBoilingAnimListener = l;
    }
```