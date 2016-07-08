
package ga.himanshu.home.inshort.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}











//public class Category implements Parcelable {
//
//    @SerializedName("id")
//    @Expose
//    private Integer id;
//    @SerializedName("title")
//    @Expose
//    private String title;
//    @SerializedName("created_at")
//    @Expose
//    private String createdAt;
//    @SerializedName("updated_at")
//    @Expose
//    private String updatedAt;
//
//    protected Category(Parcel in) {
//        title = in.readString();
//        createdAt = in.readString();
//        updatedAt = in.readString();
//    }
//
//    public static final Creator<Category> CREATOR = new Creator<Category>() {
//        @Override
//        public Category createFromParcel(Parcel in) {
//            return new Category(in);
//        }
//
//        @Override
//        public Category[] newArray(int size) {
//            return new Category[size];
//        }
//    };
//
//    /**
//     *
//     * @return
//     *     The id
//     */
//    public Integer getId() {
//        return id;
//    }
//
//    /**
//     *
//     * @param id
//     *     The id
//     */
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    /**
//     *
//     * @return
//     *     The title
//     */
//    public String getTitle() {
//        return title;
//    }
//
//    /**
//     *
//     * @param title
//     *     The title
//     */
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    /**
//     *
//     * @return
//     *     The createdAt
//     */
//    public String getCreatedAt() {
//        return createdAt;
//    }
//
//    /**
//     *
//     * @param createdAt
//     *     The created_at
//     */
//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    /**
//     *
//     * @return
//     *     The updatedAt
//     */
//    public String getUpdatedAt() {
//        return updatedAt;
//    }
//
//    /**
//     *
//     * @param updatedAt
//     *     The updated_at
//     */
//    public void setUpdatedAt(String updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(title);
//        dest.writeString(createdAt);
//        dest.writeString(updatedAt);
//    }
//}
