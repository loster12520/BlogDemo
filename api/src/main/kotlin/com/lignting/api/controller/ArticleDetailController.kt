package com.lignting.api.controller

import com.lignting.api.model.*
import com.lignting.api.services.IArticleDetailServices
import org.apache.shiro.authz.annotation.RequiresPermissions
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
class ArticleDetailController(
    private val articleDetailServices: IArticleDetailServices
) {
    private val logger = LoggerFactory.getLogger(ArticleDetailController::class.java)

    @GetMapping("/public/article/{id}")
    @RequiresPermissions("article:view")
    fun getByArticleInformationId(@PathVariable id: Long): ResponseMessage<ArticleDetail?> =
        articleDetailServices.getByArticleInformationId(id).success()

    @GetMapping("/public/article/list")
    @RequiresPermissions("article:view")
    fun getList() = articleDetailServices.getList().success()

    @PostMapping("/admin/article/add")
    @RequiresPermissions("article:add")
    fun add(@RequestBody articleDetailDTO: ArticleDetailDTO): ResponseMessage<out Any> {
        return try {
            articleDetailServices.add(articleDetailDTO).success()
        } catch (e: Exception) {
            logger.error("Something went wrong", e)
            articleDetailDTO.failed(e.message!!)
        }
    }
}