package com.lignting.api.controller

import com.lignting.api.models.*
import com.lignting.api.services.IArticleDetailService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
class ArticleDetailController(
    private val articleDetailServices: IArticleDetailService
) {
    private val logger = LoggerFactory.getLogger(ArticleDetailController::class.java)

    @GetMapping("/public/article/{id}")
    fun getByArticleInformationId(@PathVariable id: Long): Any? =
        articleDetailServices.getByArticleInformationId(id)

    @GetMapping("/public/article/list")
    fun getList(): Any? = articleDetailServices.getList()

    @PostMapping("/user/article/add")
    fun add(@RequestBody articleDetailDTO: ArticleDetailDTO): Any? =
        articleDetailServices.add(articleDetailDTO)

}