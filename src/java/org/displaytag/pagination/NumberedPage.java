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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.displaytag.util.ShortToStringStyle;


/**
 * Object representing a page.
 * @author Fabrizio Giustina
 * @author milene 
 * @version $Revision: 1.11 $ ($Author: fgiust $)
 */
public class NumberedPage
{

    /**
     * page number.
     */
    private int number;
    
    /**
     * page group
     */
    private int group;
    
    

    /**
     * is the page selected?
     */
    private boolean selected;

    /**
     * Creates a new page with the specified number and group.
     * @param pageNumber page number
     * @param isSelected is the page selected?
     * @param group group number
     */
    public NumberedPage(int pageNumber, boolean isSelected, int group)
    {
        this.number = pageNumber;
        this.selected = isSelected;
        this.group = group;
    }

    /**
     * Returns the page number.
     * @return the page number
     */
    public int getNumber()
    {
        return this.number;
    }

    /**
     * is the page selected?
     * @return true if the page is slected
     */
    public boolean getSelected()
    {
        return this.selected;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return new ToStringBuilder(this, ShortToStringStyle.SHORT_STYLE) //
            .append("selected", this.selected) //$NON-NLS-1$
            .append("number", this.number)
            .append("group", this.group)//$NON-NLS-1$
            .toString();
    }

    /**
     * Returns the group number.
     * @return the group number
     */
	public int getGroup() {
		return group;
	}
}