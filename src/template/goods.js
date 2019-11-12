//商品分类
$(function(){
	$(".menu-goods").append(ContGoods());
})	

function ContGoods(){
	var cont = "<ul class=\"cont\">";
	<#list goodsList as goods>
	<#if goods.good_class_id?length == 3>
	cont += "<li class=\"li${goods_index+1}\">"
	     + "<div class=\"menu1\">"
	     + "<a href=\"/home/kind/${goods.good_class_id}\" style=\"cursor: pointer;\">${goods.label_name}"
	     + "</a><i></i>"
	     + "</div>"
	     + "<div class=\"menu2\">"
	     + "<div class=\"div2-inner\">";
				<#if goods.getChildlist() ??>
				<#list goods.getChildlist() as good>
				cont += "<dl class=\"menu-dl\">"
					 + "<dt style=\"cursor: pointer;\"><a href=\"/home/kind/${good.good_class_id}\" style=\"cursor: pointer;\">${good.label_name}</a></dt>";
				cont += "<dd>";
					<#if good.getChildlist()??> 
						<#list good.getChildlist() as god>
						<#if god.hot_spot?number == 2>
						cont += "<a style=\"cursor: pointer;\" class=\"hot\" href=\"/home/kind/${god.good_class_id}\">${god.label_name}</a>";
						<#else>
						cont += "<a style=\"cursor: pointer;\"  href=\"/home/kind/${god.good_class_id}\">${god.label_name}</a>";
						</#if>
						</#list> 
					</#if>
				cont +="</dd>";
				cont += "</dl>";
				</#list>
				</#if>
				cont += "</div></div></li> ";
	</#if>
	</#list>
	cont += "</ul>";
	return cont;
}
