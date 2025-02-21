<template>
  <div>
    <div class="card" style="height: 50px; font-size: 18px;">您好！{{ data.user?.name }}，欢迎进入 贺卡管理系统，祝您今天过得开心</div>
  </div>
  <div style="margin-top: 10px">
    <el-row :gutter="10">
      <el-col :span="12">
        <el-card>
          <el-descriptions :column="1" title="SpringBoot3+Vue3轻鱼代码生成器" border>
            <el-descriptions-item label-class-name="labelStyle" label="简介">
              <div>SpringBoot3+Vue3轻鱼代码生成器可以通过页面上一些配置可一键生成后端所有的代码类、前端页面逻辑以及路由，并提供详细教程。</div>
            </el-descriptions-item>
            <el-descriptions-item label-class-name="labelStyle" label="技术栈">
              <div style="margin-bottom: 5px">后端：SpringBoot3 + MyBatis + Hutool</div>
              <div style="margin-bottom: 5px">前端：Vue3 + Element-Plus + Vue-Router + Axios</div>
              <div style="margin-bottom: 5px">数据库：Mysql 5.7 / 8.0</div>
              <div style="margin-bottom: 5px">编程工具：IDEA、Navicat</div>
              <div>模式：前后端分离</div>
            </el-descriptions-item>
            <el-descriptions-item label="教程">
              <a target="_blank" href="https://www.javaxmsz.cn/article?uuid=92f433f4b2264efb85caf26be771e3c5" style="font-weight: bold">SpringBoot3+Vue3轻鱼代码生成器使用教程</a>
            </el-descriptions-item>
            <el-descriptions-item label="作者">
              <a target="_blank" href="https://space.bilibili.com/432113931">武哥聊编程</a>、
              <a target="_blank" href="https://space.bilibili.com/402779077">程序员青戈</a>
            </el-descriptions-item>
            <el-descriptions-item label="公众号">
              <a target="_blank" href="https://mp.weixin.qq.com/s/a4Jy0XP6D-0akkD9iwWgEA">武哥聊编程</a>、
              <a target="_blank" href="https://mp.weixin.qq.com/s/xAUHIOQ9v6N0TBDIU4DJmw">程序员青戈</a>
            </el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>

      <el-col :span="12">
        <el-card>
          <el-descriptions :column="1" title="学习更多高质量原创实战项目，请到Java项目网" border>
            <el-descriptions-item label-class-name="labelStyle" label="网站首页" width="10">
              <div style="margin-bottom: 5px"><a href="https://www.javaxm.cn" target="_blank">https://www.javaxm.cn</a></div>
            </el-descriptions-item>
            <el-descriptions-item label="免费教程">
              <a target="_blank" href="https://javaxm.cn/free/intro.html">免费学习教程资料汇总</a>
            </el-descriptions-item>
            <el-descriptions-item label="从0带做">
              <a target="_blank" href="https://javaxm.cn/star/intro_v2.html">从0带做实战项目汇总</a>
            </el-descriptions-item>
            <el-descriptions-item label="原创精品">
              <a target="_blank" href="https://javaxm.cn/great/intro.html">原创精品实战项目汇总</a>
            </el-descriptions-item>
            <el-descriptions-item label="项目定制">
              <a target="_blank" href="https://javaxm.cn/light/intro.html">项目定制+项目亮点，让项目起飞</a>
            </el-descriptions-item>
            <el-descriptions-item label="企业项目">
              <a target="_blank" href="https://javaxm.cn/enterprise/intro.html">团团乐团购秒杀平台</a>
            </el-descriptions-item>
            <el-descriptions-item label="版权必究">
              <a target="_blank" href="https://www.yuque.com/xiaqing-en2ii/skflxg/kymtwvbrlyw2od6l" style="color: red; font-weight: bold">点击查看：原创项目版权说明，违法必究</a>
            </el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
  import request from "@/utils/request";
  import {reactive} from "vue";
  const data = reactive({
    user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
  })

</script>

