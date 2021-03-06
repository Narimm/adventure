/*
 * This file is part of adventure, licensed under the MIT License.
 *
 * Copyright (c) 2017-2020 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.adventure.text.serializer.gson;

import com.google.gson.JsonElement;
import java.util.Map;
import java.util.stream.Stream;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;

import static net.kyori.adventure.text.serializer.gson.StyleTest.name;

class TextComponentTest extends AbstractComponentTest<TextComponent> {
  @Override
  Stream<Map.Entry<TextComponent, JsonElement>> tests() {
    return Stream.of(
      entry(TextComponent.of("Hello, world."), json -> json.addProperty(ComponentSerializerImpl.TEXT, "Hello, world.")),
      entry(
        TextComponent.builder("c")
          .color(NamedTextColor.GOLD)
          .append(TextComponent.of("o", NamedTextColor.DARK_AQUA))
          .append(TextComponent.of("l", NamedTextColor.LIGHT_PURPLE))
          .append(TextComponent.of("o", NamedTextColor.DARK_PURPLE))
          .append(TextComponent.of("u", NamedTextColor.BLUE))
          .append(TextComponent.of("r", NamedTextColor.DARK_GREEN))
          .append(TextComponent.of("s", NamedTextColor.RED))
          .build(),
        json -> {
          json.addProperty(ComponentSerializerImpl.TEXT, "c");
          json.addProperty(StyleSerializer.COLOR, name(NamedTextColor.GOLD));
          json.add(ComponentSerializerImpl.EXTRA, array(extra -> {
            extra.add(object(item -> {
              item.addProperty(ComponentSerializerImpl.TEXT, "o");
              item.addProperty(StyleSerializer.COLOR, name(NamedTextColor.DARK_AQUA));
            }));
            extra.add(object(item -> {
              item.addProperty(ComponentSerializerImpl.TEXT, "l");
              item.addProperty(StyleSerializer.COLOR, name(NamedTextColor.LIGHT_PURPLE));
            }));
            extra.add(object(item -> {
              item.addProperty(ComponentSerializerImpl.TEXT, "o");
              item.addProperty(StyleSerializer.COLOR, name(NamedTextColor.DARK_PURPLE));
            }));
            extra.add(object(item -> {
              item.addProperty(ComponentSerializerImpl.TEXT, "u");
              item.addProperty(StyleSerializer.COLOR, name(NamedTextColor.BLUE));
            }));
            extra.add(object(item -> {
              item.addProperty(ComponentSerializerImpl.TEXT, "r");
              item.addProperty(StyleSerializer.COLOR, name(NamedTextColor.DARK_GREEN));
            }));
            extra.add(object(item -> {
              item.addProperty(ComponentSerializerImpl.TEXT, "s");
              item.addProperty(StyleSerializer.COLOR, name(NamedTextColor.RED));
            }));
          }));
        }
      ),
      entry(
        TextComponent.builder("This is a test.")
          .color(NamedTextColor.DARK_PURPLE)
          .hoverEvent(HoverEvent.showText(TextComponent.of("A test.")))
          .append(TextComponent.of(" "))
          .append(TextComponent.of("A what?", NamedTextColor.DARK_AQUA))
          .build(),
        json -> {
          json.addProperty(ComponentSerializerImpl.TEXT, "This is a test.");
          json.addProperty(StyleSerializer.COLOR, name(NamedTextColor.DARK_PURPLE));
          json.add(StyleSerializer.HOVER_EVENT, object(event -> {
            event.addProperty(StyleSerializer.HOVER_EVENT_ACTION, name(HoverEvent.Action.SHOW_TEXT));
            event.add(StyleSerializer.HOVER_EVENT_CONTENTS, object(value -> value.addProperty(ComponentSerializerImpl.TEXT, "A test.")));
          }));
          json.add(ComponentSerializerImpl.EXTRA, array(extra -> {
            extra.add(object(item -> item.addProperty(ComponentSerializerImpl.TEXT, " ")));
            extra.add(object(item -> {
              item.addProperty(ComponentSerializerImpl.TEXT, "A what?");
              item.addProperty(StyleSerializer.COLOR, name(NamedTextColor.DARK_AQUA));
            }));
          }));
        }
      )
    );
  }
}
