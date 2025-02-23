package com.lignting.api.controller

import com.lignting.api.model.ArticleInformationDTO
import com.lignting.api.model.success
import com.lignting.api.services.IArticleInformationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ArticleInformationController(private val articleInformationService: IArticleInformationService) {
    @GetMapping("/article/information")
    fun getList() = articleInformationService.getList().success()

    @PostMapping("/article/information/add")
    fun add(@RequestBody articleInformationDTO: ArticleInformationDTO) =
        articleInformationService.add(articleInformationDTO).success()
}