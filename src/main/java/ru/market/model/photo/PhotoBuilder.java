/*
 * Copyright
 */

package ru.market.model.photo;

import ru.market.model.model.ModelBuilder;

import static ru.market.util.validator.ObjectValidator.*;

public final class PhotoBuilder extends ModelBuilder<Photo, PhotoBuilder> {

    private String title;

    private String smallUrl;

    private String longUrl;

    PhotoBuilder() {
    }

    @Override
    public Photo build() {
        final Photo photo = new Photo();
        photo.setTitle(getTitle());
        photo.setSmallUrl(getSmallUrl());
        photo.setLongUrl(getLongUrl());
        return super.build(photo);
    }

    public PhotoBuilder addTitle(final String title) {
        this.title = title;
        return this;
    }

    public PhotoBuilder addSmallUrl(final String smallUrl) {
        this.smallUrl = smallUrl;
        return this;
    }

    public PhotoBuilder addLongUrl(final String longUrl) {
        this.longUrl = longUrl;
        return this;
    }

    private String getTitle() {
        return isNotEmpty(this.title) ? this.title : "";
    }

    private String getSmallUrl() {
        return isNotEmpty(this.smallUrl) ? this.smallUrl : "";
    }

    private String getLongUrl() {
        return isNotEmpty(this.longUrl) ? this.longUrl : "";
    }
}