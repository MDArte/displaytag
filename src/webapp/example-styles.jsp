<%@ include file="inc/header.jsp" %>

<% request.setAttribute( "test", new TestList(10, false) ); %>

<% String lClass = "isis";
   if( request.getParameter( "class" ) != null ) {
      lClass = request.getParameter( "class" );
	  if (!("isis".equals(lClass) || "its".equals(lClass) || "mars".equals(lClass) || "simple".equals(lClass) || "report".equals(lClass) || "mark".equals(lClass)))
	  {
		lClass="";
	  }
   }
%>


<h2><a href="./index.jsp">Examples</a> > Styles</h2>

<ul id="stylelist">
	<li><a href="example-styles.jsp?class=isis">ISIS</a></li>
	<li><a href="example-styles.jsp?class=its">ITS</a></li>
	<li><a href="example-styles.jsp?class=mars">Mars</a></li>
	<li><a href="example-styles.jsp?class=simple">Simple</a></li>
	<li><a href="example-styles.jsp?class=report">Report</a></li>
	<li><a href="example-styles.jsp?class=mark">Mark Column</a></li>
</ul>


<display:table name="test" class="<%=lClass%>">
  <display:column property="id" title="ID" class="idcol"/>
  <display:column property="name" />
  <display:column property="email" />
  <display:column property="status" class="tableCellError" />
  <display:column property="description" title="Comments"/>
</display:table>

<p>
	You actually have a lot of flexibility in how the table is displayed, but of
	course you should probably stay close to the defaults in most cases.  You adjust
	the look of the table via two methods, 1) pass through table and
	column attributes, and 2) Style sheets which are described below.
</p>

<p>
	Click through the above links to see different style examples of the same
	basic table.  Most of the differences in appearance between the tables below
	are achieved via only stylesheet changes.
</p>


<h3>Html attributes</h3>

<p>
	You can assign to the &lt;display:table&gt; tag any standard html attribute (es. cellspacing,
	cellpadding), and it will be included in the rendered table.
</p>

<p>
	Likewise, you can assign to the &lt;display:column&gt; tag any standard html attribute 
	and it will be included in any &lt;td&gt; tag of the rendered table.
	You can also specify a class to be used only for the column header (&lt;th&gt;) adding a 
	<code>headerClass</code> attribute.
<p>

<p>
	Note: the attribute <code>styleClass</code> used for the &lt;table&gt; and  &lt;column&gt; tag 
	in previous version of the taglibrary is deprecated in favor of the standard html <code>class</code> attribute.
</p>




<h3>Style Sheets</h3>

<p>
	While attributes might be the most comfortable way to change the appearance
	of your table, using style sheets is more powerful.  We use style sheets to
	make the header a dark color, make rows an alternate color, and set the fonts
	within the cells to a smaller version of verdana.  As the &lt;display:table&gt;
	tag is drawing, it assigns the following class names to elements.
</p>

<p>
	You can then create a style sheet and assign attributes such as font size,
	family, color, etc... to each of those class names and the table will be
	shown according to your styles.
</p>

<table>
	<thead>
		<tr>
			<th>class</th>
			<th>assigned to</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>odd</td>
			<td>assigned to the tr tag of all odd numbered data rows</td>
		</tr>
		<tr>
			<td>even</td>
			<td>assigned to the tr tag of all even numbered data rows</td>
		</tr>
		<tr>
			<td>sorted</td>
			<td>assigned to the th tag of the sorted column</td>
		</tr>
		<tr>
			<td>order1</td>
			<td>assigned to the th tag of the sorted column if sort order is ascending</td>
		</tr>
		<tr>
			<td>order2</td>
			<td>assigned to the th tag of the sorted column if sort order is descending</td>
		</tr>
	</tbody>
</table>


<%@ include file="inc/footer.jsp" %>
