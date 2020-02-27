这是一篇技术文档,而非过程。

# 一.分析需求逻辑,巧用DialogFragment,提高功能流畅度
由于DialogFragment可以很方便的定制尺寸和样式等优点,目前DialogFragment已经逐步代替Dialog。
然而本文要讲的是DialogFragment用于用户体验加速优化。
    对于非全屏的、一看便知的弹框,使用DialogFragment是无争议的。
    产品经理给到开发者手中的某个功能模块，往往有好几个界面，我们的目标是尽量用DialogFragment而非使用Activity组件（启动一个activity的速度肯定不如弹出一个dialogFragment快），我们如何判断哪些界面是属于弹框性质的，哪些界面适合用activity呢？很重要的原则是：从逻辑出发。如下需求
    
![Image](https://github.com/jj532655203/QuickDialogFragment/blob/master/img/entrance.png)功能入口![Image](https://github.com/jj532655203/QuickDialogFragment/blob/master/img/拍照中.png)拍照界面
![Image](https://github.com/jj532655203/QuickDialogFragment/blob/master/img/裁剪.png)裁剪界面![Image](https://github.com/jj532655203/QuickDialogFragment/blob/master/img/作业预览.png)作业各页内容预览界面
![Image](https://github.com/jj532655203/QuickDialogFragment/blob/master/img/填写作业名称.png)编辑作业名称界面![Image](https://github.com/jj532655203/QuickDialogFragment/blob/master/img/设置交作业时间.png)设置交作业时间界面
    
    这些界面都是全屏的,如果全部使用activity组件去展示,界面间的入场速度会比较慢,尤其是和拍照界面相关时。
    仔细思考你会发现，拍照界面的功能非常单一，就是为预览界面获取一张照片，裁剪+填作业名称+设置交作业时间3个界面也是同理，都是典型的弹框性质的界面，很适合用DialogFragment做。
    全屏的DialogFragment类见BaseFullScreenDialog.java文件。
    
    
# 二.非全屏DialogFragment监听框外点击事件
这是一个难点
在onStart方法中贴上这段代码即可
            Window window = mDialog.getWindow();
            if (window != null) {

                //设置diallog:点击外侧不是cancel而是hide
                window.getDecorView().setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        todo
                    }
                });
            }
