package com.khartec.waltz.jobs.clients.c1.sc1.model;

import com.khartec.waltz.jobs.Columns;
import com.khartec.waltz.jobs.clients.c1.sc1.parse.StatusToEntityLifecycleStatus;
import com.khartec.waltz.model.EntityLifecycleStatus;
import com.khartec.waltz.model.Nullable;
import org.apache.poi.ss.usermodel.Row;
import org.immutables.value.Value;

import static com.khartec.waltz.jobs.XlsUtilities.mapStrCell;
import static com.khartec.waltz.jobs.XlsUtilities.strVal;

@Value.Immutable
public abstract class BusinessSupportRow {

    public abstract String internalId();
    public abstract EntityLifecycleStatus status();
    public abstract String domainObjectId();
    public abstract String domainObjectName();
    public abstract String applicationId();
    public abstract String applicationName();

    @Nullable
    public abstract String orgObjectId();

    @Nullable
    public abstract String orgObjectName();

    @Nullable
    public abstract String startDate();

    @Nullable
    public abstract String endDate();

    public static BusinessSupportRow fromRow(Row r) {
        /*
            A Business-Support Objekt-ID
            B Business-Support Status
            C Business-Support Startdatum
            D Business-Support Enddatum
            E Domäne/Prozess Objekt-ID
            F Domäne/Prozess
            G Anwendung Objekt-ID
            H Anwendung
            I Organisation Objekt-ID
            J Organisation
            K Business-Support-Typ
        */
        return ImmutableBusinessSupportRow.builder()
                .internalId(strVal(r, Columns.A))
                .status(mapStrCell(r, Columns.B, StatusToEntityLifecycleStatus::apply))
                .startDate(strVal(r, Columns.C))
                .endDate(strVal(r, Columns.D))
                .domainObjectId(strVal(r, Columns.E))
                .domainObjectName(strVal(r, Columns.F))
                .applicationId(strVal(r, Columns.G))
                .applicationName(strVal(r, Columns.H))
                .orgObjectId(strVal(r, Columns.I))
                .orgObjectName(strVal(r, Columns.J))
                .build();
    }
}
