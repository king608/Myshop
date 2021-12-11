# 网上购物商店java web课程设计

## 一、功能要求以及环境

 **1.功能要求：**
1.实现商家入驻、审核、发布
2.实现商家登录、商品发布
3.实现用户的注册、登录
4.实现商品列表、分类展示
5.模拟商品购买
6.模拟商品发货、用户收货、确认

**2.运用的工具(环境)：** eclipse+Tomcat7+MySQL+servlet+EL表达式+smartupload.jar+jdk1.8

> 前言：刚开始看着这个一脸懵，不知道怎么写。连servlet这东西的原理，也不太懂。于是找了各种途径去学习，最后在B站看到一个老师讲的课程挺好的，老师手把手教你写代码，改bug，可以说是从0开始。于是跟着他学完了47课，之后自己完善代码。在这里安利一波这个老师的视频：[视频链接](https://www.bilibili.com/video/BV1zE411Y7Mg?p=1)

## 二、模块介绍

> 通过题目的要求，我们把整个的功能分为三个模块：管理员、用户、商家，采取多用户、多商家模式，每个用户和商家都有session以及表记录其购物、发布商品等。
管理员的功能是可以审核商家的入驻，对用户进行增删改查，对商品的分类调整等。商家的功能是发布自己的商品，然后对属于自己商品的订单进行发货、用户收货后确认等功能。用户的功能是购物。

![image](https://user-images.githubusercontent.com/56424284/145669889-f0d065aa-d8cb-4ded-91c3-e6ee8da25980.png)

## 三、数据结构
数据库表设计：
![image](https://user-images.githubusercontent.com/56424284/145671007-db76c7bd-b61f-49c7-8e0e-c59c96270978.png)
![image](https://user-images.githubusercontent.com/56424284/145671020-77bf65d1-10ef-4951-90be-832dc757fc2a.png)
![image](https://user-images.githubusercontent.com/56424284/145671025-b73edc25-4bc1-48a7-aa78-8ea8c11f3dac.png)
![image](https://user-images.githubusercontent.com/56424284/145671031-63d99a9e-c701-45ec-8a36-7cf2255ce8f7.png)
![image](https://user-images.githubusercontent.com/56424284/145671035-4a3db456-9fd1-4e3d-b280-f950aebfdfae.png)

## 四、实验最终效果
**三个角色：多商家多用户模式**
**管理员:**
![image](https://user-images.githubusercontent.com/56424284/145671064-fb18709a-8557-4dcf-9aaf-ee118e0cabde.png)
![image](https://user-images.githubusercontent.com/56424284/145671072-3a05fe9b-6fe0-421b-9582-e1c4507f9119.png)
**商家**
![image](https://user-images.githubusercontent.com/56424284/145671081-1eff284a-e11a-47be-b55c-126be6d465b1.png)
![image](https://user-images.githubusercontent.com/56424284/145671099-39692815-d57c-4d4e-a57f-aca510554d43.png)
**用户：**
**首页：分类浏览商品**
![image](https://user-images.githubusercontent.com/56424284/145671131-1b00cc96-c6e7-44f8-b2fe-5e1b303cc279.png)
**商品详情页**
![image](https://user-images.githubusercontent.com/56424284/145671155-dcbe8390-8a8b-457e-b944-7941d1bcc819.png)
**购物车页面**
![image](https://user-images.githubusercontent.com/56424284/145671169-63a0d359-247b-496a-9473-f852512a043f.png)
**用户订单管理页面**
![image](https://user-images.githubusercontent.com/56424284/145671176-cc3e7349-8255-4b80-8590-0723c59d0d21.png)

## 五、实验结果分析
整个购物商店完成了题目中的所有要求，并且有较好的用户体验感。优点就是有不错的体验感，使用时没有明显的bug，还有一些增加功能比如：类似推荐、最近浏览等。当然还有很多待改进的地方，比如没登录的的时候点击购物车明明设置了提示未登录跳转到登录页面，结果却没有。像可以弄一个商品的搜索功能，还有订单评论功能等等。


