package com.tramalho.rest.spring.boot.file.vo

data class FileResponseVO(val fileName: String, val fileDownloadURI: String, val fileType: String, val size: Long)
