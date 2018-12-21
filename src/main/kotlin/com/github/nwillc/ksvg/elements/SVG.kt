/*
 * Copyright 2018 nwillc@gmail.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby
 * granted, provided that the above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER
 * IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */

package com.github.nwillc.ksvg.elements

import com.github.nwillc.ksvg.SvgTagMarker
import com.github.nwillc.ksvg.attributes.AttributeType
import com.github.nwillc.ksvg.attributes.HasDimensions
import com.github.nwillc.ksvg.attributes.AttributeProperty

/**
 * The SVG element itself.
 */
@SvgTagMarker
class SVG(validation: Boolean = false) : Container("svg", validation), HasDimensions {
    /**
     * Top level functions.
     */
    companion object {
        /**
         * Create an SVG element.
         */
        fun svg(validation: Boolean = false, block: SVG.() -> Unit): SVG = SVG(validation).apply(block)
    }

    /**
     * The mode to employ when rendering. Some Elements must render differently when used inline in HTML5 or in a
     * standalone SVG file.
     */
    enum class RenderMode {
        /**
         * Render as inline SVG designed to appear inline in HTML5.
         */
        INLINE,
        /**
         * Render as an SVG `.svg` file format.
         */
        FILE
    }

    override var height: String? by AttributeProperty(type = AttributeType.LengthOrPercentage)
    override var width: String? by AttributeProperty(type = AttributeType.LengthOrPercentage)

    /**
     * The viewBox attribute.
     */
    var viewBox: String? by AttributeProperty(type = AttributeType.NumberList)

    /**
     * Create a group element in this svg.
     */
    fun defs(block: DEFS.() -> Unit): DEFS = add(DEFS(validation), block)

    /**
     * Create a style element.
     */
    fun style(block: STYLE.() -> Unit): STYLE = add(STYLE(validation), block)

    override fun getAttributes(renderMode: RenderMode): Map<String, String?> {
        return if (renderMode == RenderMode.FILE) {
            val map = mutableMapOf<String, String?>("xmlns" to "http://www.w3.org/2000/svg")
            map.putAll(attributes)
            map
        } else {
            super.getAttributes(renderMode)
        }
    }

    override fun render(appendable: Appendable, renderMode: RenderMode) {
        if (renderMode == RenderMode.FILE) {
            appendable.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n")
        }
        super.render(appendable, renderMode)
    }
}
