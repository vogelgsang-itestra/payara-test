package com.itestra.app;

import org.eclipse.persistence.history.HistoryPolicy;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.internal.helper.IdentityHashSet;
import org.eclipse.persistence.internal.sessions.AbstractRecord;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.queries.ModifyQuery;
import org.eclipse.persistence.queries.ObjectLevelModifyQuery;

import javax.enterprise.inject.spi.CDI;
import java.util.Set;

public class CustomHistoryPolicy extends HistoryPolicy {

    private static final long serialVersionUID = -5950334160374896409L;

    @Override
    public Object getCurrentTime(AbstractSession session) {
        CustomRequestContext context = CDI.current().select(CustomRequestContext.class).get();
        return context.getCurrentTimestamp();
    }

    @Override
    public void postInsert(ObjectLevelModifyQuery writeQuery) {
        writeQuery = withoutDuplicateFields(writeQuery);
        super.postInsert(writeQuery);
    }

    @Override
    public void postDelete(ModifyQuery deleteQuery) {
        deleteQuery = withoutDuplicateFields(deleteQuery);
        super.postDelete(deleteQuery);
    }

    @Override
    public void postUpdate(ObjectLevelModifyQuery writeQuery) {
        writeQuery = withoutDuplicateFields(writeQuery);
        super.postUpdate(writeQuery);
    }

    @Override
    public void postUpdate(ObjectLevelModifyQuery writeQuery, boolean isShallow) {
        writeQuery = withoutDuplicateFields(writeQuery);
        super.postUpdate(writeQuery, isShallow);
    }

    /**
     * Eclipse link generates duplicates for some inserts in the {@link ModifyQuery#getModifyRow() modify row}.
     * The {@link HistoryPolicy} does not handle the duplicates correctly, so remove them first.
     *
     * @param writeQuery the write query to clone and clean up
     * @param <T>        concrete type of the query
     * @return a clone of the writeQuery with all duplicates removed from the
     * {@link ModifyQuery#getModifyRow() row}.
     */
    @SuppressWarnings("unchecked")
    private <T extends ModifyQuery> T withoutDuplicateFields(T writeQuery) {
        writeQuery = (T) writeQuery.clone();
        final AbstractRecord modifyRow = writeQuery.getModifyRow();
        // store unique fields
        final Set<DatabaseField> fields = new IdentityHashSet(modifyRow.getFields());
        for (int i = modifyRow.getFields().size() - 1; i >= 0; --i) {
            final DatabaseField field = modifyRow.getFields().get(i);
            if (!fields.remove(field)) {
                // remove duplicate
                modifyRow.remove(field);
            }
        }
        return writeQuery;
    }
}
