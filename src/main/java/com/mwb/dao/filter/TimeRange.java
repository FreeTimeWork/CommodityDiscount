package com.mwb.dao.filter;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.mwb.util.DateTimeUtility;
import org.apache.commons.lang.StringUtils;

/**
 * Created by fangchen.chai on 2017/4/16.
 */
public class TimeRange {
    private Date from;
    private Date to;

    public TimeRange() {
    }

    public TimeRange(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return this.from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return this.to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public static TimeRange toTimeRange(String beginDate, String endDate) throws ParseException {
        if(!StringUtils.isEmpty(beginDate) && !StringUtils.isEmpty(endDate)) {
            TimeRange range = new TimeRange();
            range.setFrom(DateTimeUtility.parseYYYYMMDD(beginDate));
            range.setTo(new Date(DateTimeUtility.parseYYYYMMDD(endDate).getTime() + TimeUnit.DAYS.toMillis(1L)));
            return range;
        } else {
            return null;
        }
    }
}
