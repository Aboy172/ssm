//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cym.atcrowdfunding05commonutil.utils;

import java.util.UUID;

/**
 * @author cym
 */
public class UUIDUtil {
    public UUIDUtil () {
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
