package com.lignting.api.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.beans.BeanUtils

@Entity
data class ArticleInformation(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0L,
    var title: String = "",
    var author: String = "",
    var description: String = "",
)

data class ArticleInformationDTO(
    var title: String = "",
    var author: String = "",
    var description: String = "",
)

fun ArticleInformation.toDTO(): ArticleInformationDTO {
    val result = ArticleInformationDTO()
    BeanUtils.copyProperties(this, result)
    return result
}

fun ArticleInformationDTO.toBase(): ArticleInformation {
    val result = ArticleInformation()
    BeanUtils.copyProperties(this, result)
    return result
}