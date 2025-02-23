package com.lignting.api.controller

import com.lignting.api.model.*
import com.lignting.api.services.IArticleDetailServices
import org.springframework.web.bind.annotation.*

@RestController
class ArticleDetailController(
    private val articleDetailServices: IArticleDetailServices
) {
    @GetMapping("/article/{id}")
    fun getByArticleInformationId(@PathVariable id: Long): ResponseMessage<ArticleDetail?> =
        articleDetailServices.getByArticleInformationId(id).success()

    @GetMapping("/article/list")
    fun getList() = articleDetailServices.getList().success()

    @PostMapping("/article/add")
    fun add(@RequestBody articleDetailDTO: ArticleDetailDTO): ResponseMessage<out Any> {
        return try {
            articleDetailServices.add(articleDetailDTO).success()
        } catch (e: Exception) {
            articleDetailDTO.failed(e.message!!)
        }
    }
}