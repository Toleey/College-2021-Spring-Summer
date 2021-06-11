<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<h2>${hello}</h2>

<#-- 下面的实例大家可以自己试, 具体我就不描述了,
         -->

<#--<#if hello != "free">-->
<#--不等于free-->
<#--<#else>-->
<#--等于free-->
<#--</#if>-->

<!-- 判断hello遍历是否存在 true 则显示if内的内容 -->
<#--<#if hello??><h2>${hello}</h2></#if>-->

<#--遍历List数组, userList为后台数据键, user 则为遍历赋值对象-->
<#--<#list userList as user>-->
<#--${user.id}-->
<#--${user.name}-->
<#--${user.sex}-->
<#--${user.age}-->
<#--${user.address}-->
<#--</#list>-->

</body>
</html>