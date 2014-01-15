/**
 * Licensed under the Artistic License; you may not use this file
 * except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://displaytag.sourceforge.net/license.html
 *
 * THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */
package org.displaytag.pagination;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.util.Href;
import org.displaytag.util.ShortToStringStyle;


/**
 * Helper class for generation of paging banners.
 * @author Fabrizio Giustina
 * @author milene
 * @version $Revision: 1.14 $ ($Author: fgiust $)
 */
public class Pagination
{

    /**
     * logger.
     */
    private static Log log = LogFactory.getLog(Pagination.class);

    /**
     * Base href for urls.
     */
    private Href href;
    
    /**
     * Base href for previous, first and next urls
     */
    private Href hrefAction;
    
    /**
     * page parameter name.
     */
    private String pageParam;
    
    /**
     * table parameter name
     */
    private String tableParam;
    
   /**
    * table name
    */
    private String tableName;
    
    /**
     * group parameter name
     */
    private String groupParam;
      

    /**
     * first page.
     */
    private Integer firstPage;

    /**
     * last page.
     */
    private Integer lastPage;

    /**
     * previous page.
     */
    private Integer previousPage;

    /**
     * next page.
     */
    private Integer nextPage;

    /**
     * current page.
     */
    private Integer currentPage;
    
    /** 
     * current group.
     */
    private Integer currentGroup;
    
    /** 
     * next group.
     */
    private Integer nextGroup;    
    
    /** 
     * previous group.
     */
    private Integer previousGroup;  
    
    /** 
     * first group.
     */
    private Integer frstGroup;   
    
    
    private HttpSession session;

    /**
     * List containg NumberedPage objects.
     * @see org.displaytag.pagination.NumberedPage
     */
    private List pages = new ArrayList();

    /**
     * Constructor for Pagination.
     * @param baseHref Href used for links
     * @param pageParameter name for the page parameter
     * @param baseHrefAction Href uses for first, previous and next links
     * @param groupParameter name for the group parameter
     */
    public Pagination(Href baseHref, Href baseHrefAction, String pageParameter, String groupParameter, String tableParameter, String tableName, HttpSession session)
    {
        this.href = baseHref;
        this.pageParam = pageParameter;
        this.groupParam= groupParameter;
        this.hrefAction = baseHrefAction;
        this.tableParam = tableParameter;
        this.tableName = tableName;
        this.session = session;
    }

    /**
     * Adds a page.
     * @param number int page number
     * @param isSelected is the page selected?
     * @param group int group number
     */
    public void addPage(int number, boolean isSelected, int group)
    {
        if (log.isDebugEnabled())
        {
            log.debug("adding page " + number);
        }
        this.pages.add(new NumberedPage(number, isSelected, group));
    }

    /**
     * first page selected?
     * @return boolean
     */
    public boolean isFirst()
    {
        return this.firstPage == null;
    }

    /**
     * last page selected?
     * @return boolean
     */
    public boolean isLast()
    {
        return this.lastPage == null;
    }

    /**
     * only one page?
     * @return boolean
     */
    public boolean isOnePage()
    {
        return (this.pages == null) || this.pages.size() <= 1;
    }

    /**
     * Gets the number of the first page.
     * @return Integer number of the first page
     */
    public Integer getFirst()
    {
        return this.firstPage;
    }

    /**
     * Sets the number of the first page.
     * @param first Integer number of the first page
     */
    public void setFirst(Integer first)
    {
        this.firstPage = first;
    }

    /**
     * Gets the number of the last page.
     * @return Integer number of the last page
     */
    public Integer getLast()
    {
        return this.lastPage;
    }

    /**
     * Sets the number of the last page.
     * @param last Integer number of the last page
     */
    public void setLast(Integer last)
    {
        this.lastPage = last;
    }

    /**
     * Gets the number of the previous page.
     * @return Integer number of the previous page
     */
    public Integer getPrevious()
    {
        return this.previousPage;
    }

    /**
     * Sets the number of the previous page.
     * @param previous Integer number of the previous page
     */
    public void setPrevious(Integer previous)
    {
        this.previousPage = previous;
    }

    /**
     * Gets the number of the next page.
     * @return Integer number of the next page
     */
    public Integer getNext()
    {
        return this.nextPage;
    }

    /**
     * Sets the number of the next page.
     * @param next Integer number of the next page
     */
    public void setNext(Integer next)
    {
        this.nextPage = next;
    }

    /**
     * Sets the number of the current page.
     * @param current number of the current page
     */
    public void setCurrent(Integer current)
    {
        this.currentPage = current;
    }

    /**
     * Returns the appropriate banner for the pagination.
     * @param numberedPageFormat String to be used for a not selected page
     * @param numberedPageSelectedFormat String to be used for a selected page
     * @param numberedPageSeparator separator beetween pages
     * @param fullBanner String basic banner
     * @return String formatted banner whith pages
     */
    public String getFormattedBanner(String numberedPageFormat, String numberedPageSelectedFormat,
        String numberedPageSeparator, String fullBanner)
    {
        StringBuffer buffer = new StringBuffer(100);

        // numbered page list
        Iterator pageIterator = this.pages.iterator();

        while (pageIterator.hasNext())
        {

            // get NumberedPage from iterator
            NumberedPage page = (NumberedPage) pageIterator.next();

            Integer pageNumber = new Integer(page.getNumber());
            this.href.removeParameter(this.groupParam);
            String urlString = ((Href) this.href.clone()).addParameter(this.pageParam, pageNumber).addParameter(this.tableParam, tableName).toString();
            

            // needed for MessageFormat : page number/url
            Object[] pageObjects = {pageNumber, urlString};

            // selected page need a different formatter
            if (page.getSelected())
            {
            	
                buffer.append(MessageFormat.format(numberedPageSelectedFormat, pageObjects));
                //if(tableName != null)
                	session.setAttribute(tableName, page);
                	Collection tableNamesList = null;
                	if(session.getAttribute("tableNameList") != null){
                		tableNamesList = (Collection)session.getAttribute("tableNameList");
                		if(!tableNamesList.contains(tableName)){
                			tableNamesList.add(tableName);
                		}                		
                	}else{
                		tableNamesList = new ArrayList();
                		tableNamesList.add(tableName);
                	}
                	session.setAttribute("tableNameList", tableNamesList);
                
            }
            else
            {
                buffer.append(MessageFormat.format(numberedPageFormat, pageObjects));
            }

            // next? add page separator
            if (pageIterator.hasNext())
            {
                buffer.append(numberedPageSeparator);
            }
        }

        // String for numbered pages
        String numberedPageString = buffer.toString();

        // Object array
        // {0} full String for numbered pages
        // {1} first group url
        // {2} previous group url
        // {3} next group url
        // {4} current page
        // {5} total pages
        this.hrefAction.removeParameter(this.pageParam);
        Object[] pageObjects = {
        numberedPageString,
        ((Href) this.hrefAction.clone()).addParameter(this.pageParam, 1).addParameter(this.tableParam, tableName),
        ((Href) this.hrefAction.clone()).addParameter(this.pageParam, ((getGroupFromPage()-1) * 10 )).addParameter(this.tableParam, tableName),
        ((Href) this.hrefAction.clone()).addParameter(this.pageParam, (getGroupFromPage() * 10 + 1) ).addParameter(this.tableParam, tableName),
        //((Href) this.href.clone()).addParameter(this.pageParam, getLast()),
        this.currentPage,
        new Integer(pages.size())}; 
        // return the full banner
        return MessageFormat.format(fullBanner, pageObjects);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return new ToStringBuilder(this, ShortToStringStyle.SHORT_STYLE) //
            .append("firstPage", this.firstPage) //$NON-NLS-1$
            .append("lastPage", this.lastPage) //$NON-NLS-1$
            .append("currentPage", this.currentPage) //$NON-NLS-1$
            .append("nextPage", this.nextPage) //$NON-NLS-1$
            .append("previousPage", this.previousPage) //$NON-NLS-1$
            .append("pages", this.pages) //$NON-NLS-1$
            .append("href", this.href) //$NON-NLS-1$
            .append("pageParam", this.pageParam) //$NON-NLS-1$
            .toString();
    }

	public void setCurrentGroup(Integer currentGroup) {
		this.currentGroup = currentGroup;
	}
	
	protected Integer getGroupFromPage()
	{
		int extra = 0;
        if (this.currentPage % 10 == 0) extra = 1;
        
        return this.currentPage/10 - extra + 1;
	}
}