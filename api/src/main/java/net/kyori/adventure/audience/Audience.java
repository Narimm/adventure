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
package net.kyori.adventure.audience;

import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import java.util.function.Consumer;

/**
 * A receiver of text-based media.
 */
public interface Audience {
  /**
   * Gets an audience that does nothing.
   *
   * @return an audience
   */
  static Audience.@NonNull Everything empty() {
    return EmptyAudience.INSTANCE;
  }

  /**
   * Widens {@code audience} to implement {@link Audience.Everything all operations},
   * failing silently with a no-op when a method isn't supported.
   *
   * @param audience the audience
   * @return a forwarding audience
   */
  static Audience.@NonNull Everything of(final @NonNull Audience audience) {
    return (ForwardingAudience) () -> audience;
  }

  /**
   * Creates an audience that weakly delegates to another audience.
   *
   * @param audience the delegate audience
   * @return an audience
   */
  static @NonNull Audience weakOf(final @Nullable Audience audience) {
    return audience instanceof WeakAudience || audience == EmptyAudience.INSTANCE ? audience : new WeakAudience(audience);
  }

  /**
   * Applies the given {@code action} onto the audience, and returns an
   * {@link Audience} encapsulating the sub-audiences (if any) which didn't support
   * the action.
   *
   * @param type the type of audience the action requires
   * @param action the action
   * @param <T> the type of audience
   * @return a {@link Audience} of the sub-audiences the action couldn't be applied to
   */
  default <T extends Audience> @NonNull Audience perform(final @NonNull Class<T> type, final @NonNull Consumer<T> action) {
    if(this instanceof StubAudience) {
      throw new RuntimeException("StubAudience implementations must override this method");
    }
    if(type.isInstance(this)) {
      action.accept(type.cast(this));
      return Audience.empty();
    } else {
      return this;
    }
  }

  /**
   * An audience that supports everything.
   */
  interface Everything extends
    Audience,
    Audience.Message,
    Audience.ActionBar,
    Audience.Title,
    Audience.BossBar,
    Audience.Sound,
    Audience.Book {
  }

  /**
   * An audience that supports messages.
   */
  interface Message extends Audience {
    /**
     * Sends a message.
     *
     * @param message the message
     */
    void sendMessage(final @NonNull Component message);
  }

  /**
   * An audience that supports action bars.
   */
  interface ActionBar extends Audience {
    /**
     * Sends a message on the action bar.
     *
     * @param message the message
     */
    void sendActionBar(final @NonNull Component message);
  }

  /**
   * An audience that supports titles.
   */
  interface Title extends Audience {
    /**
     * Shows a title.
     *
     * @param title the title
     */
    void showTitle(final @NonNull Title title);

    /**
     * Clears the currently displayed title.
     */
    void clearTitle();

    /**
     * Resets the title, subtitle, fade-in time, stay time, and fade-out time back to "unset".
     */
    void resetTitle();
  }

  /**
   * An audience that supports boss bars.
   */
  interface BossBar extends Audience {
    /**
     * Shows a bossbar.
     *
     * @param bar the bossbar
     */
    void showBossBar(final @NonNull BossBar bar);

    /**
     * Hides a bossbar.
     *
     * @param bar the bossbar
     */
    void hideBossBar(final @NonNull BossBar bar);
  }

  /**
   * An audience that supports sounds.
   */
  interface Sound extends Audience {
    /**
     * Plays a sound.
     *
     * @param sound the sound
     */
    void playSound(final @NonNull Sound sound);

    /**
     * Plays a sound.
     *
     * @param sound the sound
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    void playSound(final @NonNull Sound sound, final double x, final double y, final double z);

    /**
     * Stops all sounds.
     *
     * @param stop the stop
     */
    void stopSound(final @NonNull SoundStop stop);
  }

  /**
   * An audience that supports books.
   */
  interface Book extends Audience {
    /**
     * Opens a book.
     *
     * <p>Opens a virtual book for the client, no item will be persisted.</p>
     *
     * @param book the book
     */
    void openBook(final @NonNull Book book);
  }
}
