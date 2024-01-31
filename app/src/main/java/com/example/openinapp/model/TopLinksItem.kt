package com.example.openinapp.model

data class TopLinksItem(val app: String = "",
                        val thumbnail: Nothing? = null,
                        val smartLink: String = "",
                        val createdAt: String = "",
                        val urlId: Int = 0,
                        val isFavourite: Boolean = false,
                        val webLink: String = "",
                        val title: String = "",
                        val timesAgo: String = "",
                        val urlPrefix: String = "",
                        val domainId: String = "",
                        val urlSuffix: String = "",
                        val originalImage: String = "",
                        val totalClicks: Int = 0)