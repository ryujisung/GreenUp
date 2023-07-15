package com.example.greenup.model.remote

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ns2", strict = false)
data class NS2 (
    @field:Element(name = "channel")
    var channel: Channel? = null
)

@Root(name = "channel", strict = false)
data class Channel(
    @field:Element(name = "return")
    var returnData: Return? = null
)

@Root(name = "return", strict = false)
data class Return(
    @field:Element(name = "allCnt")
    var allCnt: Int = 0,

    @field:Element(name = "code")
    var code: Int = 0,

    @field:Element(name = "codeMsg")
    var codeMsg: String = "",

    @field:ElementList(name = "content", inline = true)
    var contentList: List<Content>? = null
)

@Root(name = "content", strict = false)
data class Content(
    @field:Element(name = "crtfcBgnde")
    var crtfcBgnde: String = "",

    @field:Element(name = "crtfcEndde")
    var crtfcEndde: String = "",

    @field:Element(name = "crtfcInsttLink")
    var crtfcInsttLink: String = "",

    @field:Element(name = "crtfcInsttNm")
    var crtfcInsttNm: String = "",

    @field:Element(name = "crtfcNo")
    var crtfcNo: String = "",

    @field:Element(name = "crtfcSn")
    var crtfcSn: String = "",

    @field:Element(name = "crtfcSttus")
    var crtfcSttus: String = "",

    @field:Element(name = "entrpsNm")
    var entrpsNm: String = "",

    @field:Element(name = "link")
    var link: String = "",

    @field:Element(name = "productClNm")
    var productClNm: String = "",

    @field:Element(name = "productNm")
    var productNm: String = ""
)
