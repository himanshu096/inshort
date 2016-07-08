package ga.himanshu.home.inshort.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Stories implements Parcelable{

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("per_page")
    @Expose
    private Integer perPage;
    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("last_page")
    @Expose
    private Integer lastPage;
    @SerializedName("next_page_url")
    @Expose
    private String nextPageUrl;
    @SerializedName("prev_page_url")
    @Expose
    private Object prevPageUrl;
    @SerializedName("from")
    @Expose
    private Integer from;
    @SerializedName("to")
    @Expose
    private Integer to;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();

    protected Stories(Parcel in) {
        nextPageUrl = in.readString();
        data = in.createTypedArrayList(Datum.CREATOR);
    }

    public static final Creator<Stories> CREATOR = new Creator<Stories>() {
        @Override
        public Stories createFromParcel(Parcel in) {
            return new Stories(in);
        }

        @Override
        public Stories[] newArray(int size) {
            return new Stories[size];
        }
    };

    /**
     *
     * @return
     *     The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     *
     * @param total
     *     The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     *
     * @return
     *     The perPage
     */
    public Integer getPerPage() {
        return perPage;
    }

    /**
     *
     * @param perPage
     *     The per_page
     */
    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    /**
     *
     * @return
     *     The currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     *
     * @param currentPage
     *     The current_page
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     *
     * @return
     *     The lastPage
     */
    public Integer getLastPage() {
        return lastPage;
    }

    /**
     *
     * @param lastPage
     *     The last_page
     */
    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    /**
     *
     * @return
     *     The nextPageUrl
     */
    public String getNextPageUrl() {
        return nextPageUrl;
    }

    /**
     *
     * @param nextPageUrl
     *     The next_page_url
     */
    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    /**
     *
     * @return
     *     The prevPageUrl
     */
    public Object getPrevPageUrl() {
        return prevPageUrl;
    }

    /**
     *
     * @param prevPageUrl
     *     The prev_page_url
     */
    public void setPrevPageUrl(Object prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    /**
     *
     * @return
     *     The from
     */
    public Integer getFrom() {
        return from;
    }

    /**
     *
     * @param from
     *     The from
     */
    public void setFrom(Integer from) {
        this.from = from;
    }

    /**
     *
     * @return
     *     The to
     */
    public Integer getTo() {
        return to;
    }

    /**
     *
     * @param to
     *     The to
     */
    public void setTo(Integer to) {
        this.to = to;
    }

    /**
     *
     * @return
     *     The data
     */
    public List<Datum> getData() {
        return data;
    }

    /**
     *
     * @param data
     *     The data
     */
    public void setData(List<Datum> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nextPageUrl);
        dest.writeTypedList(data);
    }
}
