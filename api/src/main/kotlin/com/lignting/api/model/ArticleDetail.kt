package com.lignting.api.model

import jakarta.persistence.*
import org.springframework.beans.BeanUtils


@Entity
data class ArticleDetail(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0L,
    @Lob
    var md: String = "",
    @Lob
    var html: String = "",

    @OneToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(nullable = false)
    var articleInformation: ArticleInformation? = null,
)

data class ArticleDetailDTO(
    var id: Long? = 0L,
    var md: String = "",
    var html: String = "",
    var articleInformationId: Long = 0,
)


fun ArticleDetail.toDTO(): ArticleDetailDTO {
    val result = ArticleDetailDTO()
    BeanUtils.copyProperties(this, result)
    return result
}

fun ArticleDetailDTO.toBase(): ArticleDetail {
    val result = ArticleDetail()
    BeanUtils.copyProperties(this, result)
    return result
}