# 一.DialogFragment用于用户体验加速优化
由于DialogFragment可以很方便的定制尺寸和样式等优点,目前DialogFragment已经逐步代替Dialog。
然而本文要讲的是DialogFragment用于用户体验加速优化。
    对于非全屏的、一看便知的弹框,使用DialogFragment是无争议的。
    产品经理给到开发者手中的某个功能模块，往往有好几个界面，我们的目标是尽量用DialogFragment而非使用Activity组件（启动一个activity的速度肯定不如弹出一个dialogFragment快），我们如何判断哪些界面是属于弹框性质的，哪些界面适合用activity呢？很重要的原则是：从逻辑出发。如下需求
    
# 一.DialogFragment用于用户体验加速优化
2.
