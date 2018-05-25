package ru.rymyantsev.db.model;

import lombok.*;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public final class DataTranslate {
    private String sourceWorld;
    private String translateWorld;
    private String date;
    private String ip;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataTranslate that = (DataTranslate) o;
        return Objects.equals(sourceWorld, that.sourceWorld) &&
                Objects.equals(translateWorld, that.translateWorld);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sourceWorld, translateWorld);
    }
}
