/*
 * Waltz - Enterprise Architecture
 * Copyright (C) 2016, 2017 Waltz open source project
 * See README.md for more information
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import angular from "angular";
import TaxonomyManagementStore from "./services/taxonomy-management-store";
import PendingTaxonomyChangesList from "./components/pending-taxonomy-changes-list/pending-taxonomy-changes-list";
import PendingTaxonomyChangesSubSection from "./components/pending-taxonomy-changes-sub-section/pending-taxonomy-changes-sub-section";
import TaxonomyChangeInfo from "./components/taxonomy-change-command-info/taxonomy-change-command-info";
import TaxonomyChangePreview from "./components/taxonomy-change-command-preview/taxonomy-change-command-preview";
import {registerStores, registerComponents} from "../common/module-utils";


export default () => {
    const module = angular.module("waltz.taxonomy-management", []);

    registerStores(module, [
        TaxonomyManagementStore ]);

    registerComponents(module, [
        PendingTaxonomyChangesList,
        PendingTaxonomyChangesSubSection,
        TaxonomyChangeInfo,
        TaxonomyChangePreview ]);

    return module.name;
};
