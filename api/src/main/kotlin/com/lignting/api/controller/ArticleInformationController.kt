package com.lignting.api.controller

import com.lignting.api.models.ArticleInformation
import com.lignting.api.services.IArticleInformationService
import org.springframework.web.bind.annotation.*

@RestController
class ArticleInformationController(private val articleInformationService: IArticleInformationService) {
    @GetMapping("/public/articleInformation/list")
    fun getList(): Any? = articleInformationService.getList()

    @GetMapping("/public/articleInformation/{id}")
    fun getById(@PathVariable id: Long): Any? =
        articleInformationService.get(id)

    @PostMapping("/user/articleInformation/add")
    fun add(@RequestBody articleInformation: ArticleInformation): Any? =
        articleInformationService.add(articleInformation)
}