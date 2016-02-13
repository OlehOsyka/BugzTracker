package com.bugztracker.commons.utils;

import org.bson.types.ObjectId;
import org.springframework.util.Assert;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Oleh_Osyka for EPMC-CLO
 * Date: 13.02.2016
 * Time: 19:16
 */
public final class DbUtils {

    private DbUtils() {
    }

    public static List<ObjectId> convertIds(List<String> ids) {
        Assert.notEmpty(ids, "please specify ids for convertion");
        List<ObjectId> result = newArrayList();
        for (String id : ids) {
            result.add(new ObjectId(id));
        }
        return result;
    }
}
