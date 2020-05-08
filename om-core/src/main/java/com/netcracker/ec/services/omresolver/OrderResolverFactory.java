package com.netcracker.ec.services.omresolver;

import com.netcracker.ec.exceptions.OmResolverException;
import com.netcracker.ec.model.db.NcEntity;
import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.services.db.NcObjectService;
import com.netcracker.ec.services.db.impl.NcObjectServiceImpl;
import com.netcracker.ec.services.db.impl.NcObjectTypeServiceImpl;
import com.netcracker.ec.services.omresolver.annotations.ObjectType;
import lombok.NonNull;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Set;

import static com.netcracker.ec.common.OmConstants.DOMAIN_MODEL_PACKAGE;

public class OrderResolverFactory {
    private final NcObjectService ncObjectService = new NcObjectServiceImpl();

    public static OrderResolverFactory getInstance() {
        return OmOrderResolverFactoryHolder.OM_ORDER_RESOLVER_FACTORY;
    }

    public <T extends Order> T findOrderById(@NonNull Integer id) {
        NcObject object = ncObjectService.getNcObjectById(id);
        NcObjectType objectType = object.getObjectType();

        Set<Class<?>> classes = new Reflections(DOMAIN_MODEL_PACKAGE).getTypesAnnotatedWith(ObjectType.class, true);
        Class<?> clazz = findClassForObjectType(classes, objectType);
        if (clazz == null) {
            throw new OmResolverException("Cannot find appropriate Class for Object Type: " + objectType);
        }
        return createOrder(object, clazz);
    }

    private Class<?> findClassForObjectType(Set<Class<?>> classes, NcObjectType objectType) {
        for (Class<?> clazz : classes) {
            ObjectType otAnnotation = clazz.getAnnotation(ObjectType.class);
            if (otAnnotation.objectTypeId() == objectType.getId()) {
                return clazz;
            }
        }
        Integer parentObjectTypeId = objectType.getParentId();
        if (parentObjectTypeId != null) {
            NcObjectType parentObjectType = new NcObjectTypeServiceImpl().getNcObjectTypeById(parentObjectTypeId);
            return findClassForObjectType(classes, parentObjectType);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T extends Order> T createOrder(NcObject object, Class<?> clazz) {
        try {
            Class<? extends Order> tClass = clazz.asSubclass(Order.class);
            T order = (T) tClass.newInstance();
            Field id = NcEntity.class.getDeclaredField("id");
            Field name = NcEntity.class.getDeclaredField("name");
            Field objectType = NcObject.class.getDeclaredField("objectType");

            id.setAccessible(true);
            id.set(order, object.getId());
            name.setAccessible(true);
            name.set(order, object.getName());
            objectType.setAccessible(true);
            objectType.set(order, object.getObjectType());

            return order;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new OmResolverException("Cannot create Order by id [" + object.getId() + "]", ex);
        } catch (NoSuchFieldException ex) {
            throw new OmResolverException("Cannot access fields for order class: " + clazz, ex);
        }
    }

    private static class OmOrderResolverFactoryHolder {
        static final OrderResolverFactory OM_ORDER_RESOLVER_FACTORY = new OrderResolverFactory();

        private OmOrderResolverFactoryHolder() {
        }
    }
}
