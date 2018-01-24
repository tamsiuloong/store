<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function addProduct() {
            window.location.href = "${pageContext.request.contextPath}/AdminProductServlet?method=saveUI";
        }
    </script>
</HEAD>
<body >
<div id="app">

        <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
            <TBODY>
            <tr>
                <td class="ta_01" align="center" bgColor="#afd1f3">
                    <strong>商品列表</strong>
                </TD>
            </tr>
            <tr>
                <td class="ta_01" align="right">
                    <button type="button" id="add" name="add" value="添加" class="button_add" onclick="addProduct()">
                        &#28155;&#21152;
                    </button>

                </td>
            </tr>
            <tr>
                <td class="ta_01" align="center" bgColor="#f5fafe">
                    <table cellspacing="0" cellpadding="1" rules="all"
                           bordercolor="gray" border="1" id="DataGrid1"
                           style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                        <tr
                                style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

                            <td align="center" width="18%">
                                序号
                            </td>
                            <td align="center" width="17%">
                                商品图片
                            </td>
                            <td align="center" width="17%">
                                商品名称
                            </td>
                            <td align="center" width="17%">
                                商品价格
                            </td>
                            <td align="center" width="17%">
                                是否热门
                            </td>
                            <td width="7%" align="center">
                                编辑
                            </td>
                            <td width="7%" align="center">
                                下架
                            </td>
                        </tr>
                            <tr v-for="(p,i) in page.list" onmouseover="this.style.backgroundColor = 'white'"
                                onmouseout="this.style.backgroundColor = '#F5FAFE';">
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="18%">
                                        {{i+1}}
                                </td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="17%">
                                    <img width="40" height="45" :src="'${pageContext.request.contextPath}/'+p.pimage">
                                </td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="17%">
                                    {{p.pname}}
                                </td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="17%">
                                    {{p.shop_price}}
                                </td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="17%">
                                     {{p.is_hot==0?'是':'否'}}
                                </td>
                                <td align="center" style="HEIGHT: 22px">
                                    <a href="${pageContext.request.contextPath}/AdminProductServlet?method=edit&pid=${p.pid}">
                                        <img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0"
                                             style="CURSOR: hand">
                                    </a>
                                </td>

                                <td align="center" style="HEIGHT: 22px">
                                    <a href="${pageContext.request.contextPath}/AdminProductServlet?method=pushDown&pid=${p.pid}">
                                        <img src="${pageContext.request.contextPath}/images/i_del.gif" width="16"
                                             height="16" border="0" style="CURSOR: hand">
                                    </a>
                                </td>
                            </tr>
                    </table>
                </td>
            </tr>
            <tr align="center">
                <td colspan="7">
                    第{{page.currPage}}/{{page.totalPage}}页 &nbsp; &nbsp; &nbsp;
                    总记录数:{{page.totalCount}} &nbsp; 每页显示:{{page.pageSize}}
                        <a v-if="page.currPage != 1" href="#"  @click="init(1)">首页</a>|
                        <a v-if="page.currPage != 1" href="#"  @click="init(page.prePage)">上一页</a>|
                    &nbsp; &nbsp;


                            <a href="#" @click="init(pageNo)" v-if="page.currPage != pageNo" v-for="pageNo in page.totalPage">[{{pageNo}}]</a>

                    &nbsp; &nbsp;
                        <a v-if="page.currPage != page.totalPage" @click="init(page.nextPage)">下一页</a>
                        <a v-if="page.currPage != page.totalPage" @click="init(page.totalPage)">尾页</a>
                </td>
            </tr>
            </TBODY>
        </table>
</div>
</body>

<script type="text/javascript" src="../../js/vue.js"></script>
<script type="text/javascript" src="../../js/axios.min.js"></script>
<script type="text/javascript" src="list.js"></script>
</HTML>

