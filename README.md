### 1、概述
keep 的运动统计界面，滑动的柱状图很炫酷。想着怎么实现
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200528213544350.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xja2o2ODY=,size_16,color_FFFFFF,t_70)

### 2、模仿效果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200528213738365.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xja2o2ODY=,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200528213738377.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xja2o2ODY=,size_16,color_FFFFFF,t_70)
### 3、实现思路
多操作几遍，感觉keep的效果就是一个横向的picker，及：横向滑动，自动选择中间一项，点击item也可以自动滚动该item（这个只是锦上添花）。

就按横向picker进行思考，google 了一些实现。发现用recycleView其实是很方便做横向 picker的。参考：[添加链接描述](https://medium.com/@nbtk123/create-your-own-horizontal-vertical-slider-picker-android-94b6ee32b3ff)

#### 3.1、值得提的点
- SnapHelper 可以自动滚动一个item，实现吸合效果

其他都是顺其自然的点：
- RecyclerView 可以右向左滑动
- RecyclerView 的横向加载更多（现在取临界点是利用picker取的）

### 4、补充
RecyclerView 的**横向**加载更多在使用 layoutManager.findLastVisibleItemPosition() 方法时返回的都是 -1，所以舍弃了这种方式，还没深入了解为什么只竖向有效横向无效

```
LinearLayoutManager layoutManager = ((LinearLayoutManager)recyclerView.getLayoutManager());
   recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            // super.onScrolled(recyclerView, dx, dy);
            int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
            if (lastVisiblePosition == recyclerView.getChildCount()) {
                if (loadmore) {
                    loadmore = false;
                   method();
                }
            }
        }
    });
```

### 5、附
[github]: [添加链接描述](https://github.com/lckj686/KeepTrain-master)