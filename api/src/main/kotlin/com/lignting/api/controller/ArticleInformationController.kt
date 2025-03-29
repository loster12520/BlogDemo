package com.lignting.api.controller

import com.lignting.api.model.ArticleInformation
import com.lignting.api.model.ArticleInformationDTO
import com.lignting.api.model.success
import com.lignting.api.services.IArticleInformationService
import org.apache.shiro.authz.annotation.RequiresPermissions
import org.springframework.web.bind.annotation.*

@RestController
class ArticleInformationController(private val articleInformationService: IArticleInformationService) {
    @GetMapping("/public/articleInformation/list")
    @RequiresPermissions("article:view")
    fun getList() = articleInformationService.getList().success()

    @GetMapping("/public/articleInformation/{id}")
    @RequiresPermissions("article:view")
    fun getById(@PathVariable id: Long) =
        articleInformationService.get(id).success()

    @PostMapping("/admin/articleInformation/add")
    @RequiresPermissions("article:add")
    fun add(@RequestBody articleInformation: ArticleInformation) =
        articleInformationService.add(articleInformation).success()
}