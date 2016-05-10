package com.viglle.carmanual.utils.net;

import java.io.Serializable;

/**
 * 存储健值对
 * @param <KEY>
 * @param <VALUE>
 */
public class TwoValues<KEY,VALUE> implements Serializable{
    public KEY key;
    public VALUE value;

    public TwoValues(KEY key, VALUE value) {
        this.key = key;
        this.value = value;
    }
}
