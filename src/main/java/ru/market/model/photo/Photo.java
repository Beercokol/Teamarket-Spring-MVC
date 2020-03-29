/*
 * Copyright
 */

package ru.market.model.photo;


import ru.market.model.model.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static ru.market.util.validator.ObjectValidator.*;

@Entity
@Table(name = "photos")
public final class Photo extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Путь к папке с изображениями в файловой системе.
     */
    public static final String PATH = System.getenv("CATALINA_HOME") +
            "/webapp/ROOT/resources/image/";

    /**
     * Название изображения.
     */
    @Column(name = "title", nullable = false)
    private String title = "";

    /**
     * Строка-ссылка на малое изображения.
     */
    @Column(name = "photo_link_short")
    private String smallUrl = "";

    /**
     * Строка-ссылка на большое изображения.
     */
    @Column(name = "photo_link_long")
    private String longUrl = "";

    protected Photo() {
    }

    /**
     * Возвращает описание изображения.
     */
    @Override
    public String toString() {
        return "Photo{" + super.toString() +
                ", title: " + this.title +
                ", smallUrl: " + this.smallUrl +
                ", longUrl: " + this.longUrl +
                '}';
    }

    /**
     * Сравнивает текущий объект с объектом переданым как параметр.
     */
    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Photo photo = (Photo) object;
            result = this.title.equals(photo.title) &&
                    this.smallUrl.equals(photo.smallUrl) &&
                    this.longUrl.equals(photo.longUrl);
        }
        return result;
    }

    /**
     * Возвращает хеш код объекта.
     */
    @Override
    public int hashCode() {
        int result = this.title.hashCode();
        result = 31 * result + this.smallUrl.hashCode();
        result = 31 * result + this.longUrl.hashCode();
        return result;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = isNotEmpty(title) ? title : "";
    }

    public String getSmallUrl() {
        return this.smallUrl;
    }

    public void setSmallUrl(final String smallUrl) {
        this.smallUrl = isNotEmpty(smallUrl) ? smallUrl : "";
    }

    public String getLongUrl() {
        return this.longUrl;
    }

    public void setLongUrl(final String longUrl) {
        this.longUrl = isNotEmpty(longUrl) ? longUrl : "";
    }

    public static PhotoBuilder getBuilder() {
        return new PhotoBuilder();
    }
}