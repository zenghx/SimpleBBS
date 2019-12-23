<%--
  Created by IntelliJ IDEA.
  User: zengh
  Date: 2019/12/23
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="cell" id="page_bar">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tbody>
        <tr>
            <td width="84%" align="left"></td>
            <td width="16%" align="right">
                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                    <tbody>
                    <tr>
                        <td width="25%" id="previous" align="center" class="super normal button disable_now"
                            style="border-right: none; border-top-right-radius: 0px; border-bottom-right-radius: 0px;"
                            onclick="previous_page()">❮
                        </td>
                        <td width="25%" id="next" align="center" class="super normal_page_right button"
                            style="border-top-left-radius: 0px; border-bottom-left-radius: 0px;" onclick="next_page()"
                            title="下一页">❯
                        </td>
                        <td width="50%" align="center" id="page_count"></td>
                    </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
</div>
