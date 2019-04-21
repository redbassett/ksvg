/*
 * Copyright 2019 nwillc@gmail.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL
 * WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL
 * THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING
 * FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION
 * WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 */

package com.github.nwillc.ksvg.elements

import com.github.nwillc.ksvg.attributes.AttributeProperty
import com.github.nwillc.ksvg.attributes.AttributeType
import com.github.nwillc.ksvg.attributes.HasAttributes
import com.github.nwillc.ksvg.attributes.HasAttributesImpl
import com.github.nwillc.ksvg.attributes.HasFill
import com.github.nwillc.ksvg.attributes.HasFillImpl
import com.github.nwillc.ksvg.attributes.HasOrigin
import com.github.nwillc.ksvg.attributes.HasOriginImpl

/**
 * An SVG text element.
 */
class TEXT(validation: Boolean = false, hasAttributes: HasAttributes = HasAttributesImpl(validation)) :
    Element("text", validation, hasAttributes),
    HasOrigin by HasOriginImpl(hasAttributes),
    HasFill by HasFillImpl(hasAttributes) {

    /**
     * The font size attributes.
     */
    var fontSize: String? by AttributeProperty(type = AttributeType.Length)
    /**
     * The font family.
     */
    var fontFamily: String? by AttributeProperty()
}
