package com.lignting.api.controller

import com.lignting.api.model.ArticleInformation
import com.lignting.api.model.ArticleInformationDTO
import com.lignting.api.model.success
import com.lignting.api.services.IArticleInformationService
import org.springframework.web.bind.annotation.*

@RestController
class ArticleInformationController(private val articleInformationService: IArticleInformationService) {
    @GetMapping("/article/information")
    fun getList() = articleInformationService.getList().success()

    @GetMapping("/article/information/{id}")
    fun getById(@PathVariable id: Long) =
        articleInformationService.get(id).success()

    @PostMapping("/article/information/add")
    fun add(@RequestBody articleInformation: ArticleInformation) =
        articleInformationService.add(articleInformation).success()
}