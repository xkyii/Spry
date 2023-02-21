package com.xkyii.spry.admin.manager;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import com.xkyii.spry.admin.constant.enums.*;
import com.xkyii.spry.admin.dto.data.DictionaryData;
import com.xkyii.spry.common.constant.DictionaryEnum;
import io.quarkus.cache.CacheResult;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字典管理类
 */
@ApplicationScoped
public class DictionaryManager {

    private static final String CACHE_KEY = "dictionary";

    public Map<String, List<DictionaryData>> getAll() {
        Map<String, List<DictionaryData>> map = MapUtil.newHashMap(128);
        map.put(BusinessTypeEnum.Key, arrayToList(BusinessTypeEnum.values()));
        map.put(YesOrNoEnum.Key, arrayToList(YesOrNoEnum.values()));
        map.put(StatusEnum.Key, arrayToList(StatusEnum.values()));
        map.put(GenderEnum.Key, arrayToList(GenderEnum.values()));
        map.put(NoticeStatusEnum.Key, arrayToList(NoticeStatusEnum.values()));
        map.put(NoticeTypeEnum.Key, arrayToList(NoticeTypeEnum.values()));
        map.put(OperationStatusEnum.Key, arrayToList(OperationStatusEnum.values()));
        map.put(VisibleStatusEnum.Key, arrayToList(VisibleStatusEnum.values()));
        return map;
    }

    @CacheResult(cacheName = CACHE_KEY)
    public Uni<Map<String, List<DictionaryData>>> getAllCache() {
        return Uni.createFrom().emitter(em -> em.complete(getAll()));
    }

    @SuppressWarnings("rawtypes")
    private static List<DictionaryData> arrayToList(DictionaryEnum[] dictionaryEnums) {
        if(ArrayUtil.isEmpty(dictionaryEnums)) {
            return ListUtil.empty();
        }
        return Arrays.stream(dictionaryEnums).map(DictionaryData::new).collect(Collectors.toList());
    }
}
