package com.example.openinapp.model

data class DataResponse(val topLocation: String = "",
                        val data: Data?=null,
                        val message: String = "",
                        val extraIncome: Double = 0.0,
                        val totalLinks: Int = 0,
                        val topSource: String = "",
                        val totalClicks: Int = 0,
                        val startTime: String = "",
                        val appliedCampaign: Int = 0,
                        val supportWhatsappNumber: String = "",
                        val todayClicks: Int = 0,
                        val status: Boolean = false,
                        val statusCode: Int = 0,
                        val linksCreatedToday: Int = 0)