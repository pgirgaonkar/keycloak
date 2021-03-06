/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.testsuite.util;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.keycloak.admin.client.resource.ClientAttributeCertificateResource;
import org.keycloak.admin.client.resource.ClientInitialAccessResource;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.ClientTemplateResource;
import org.keycloak.admin.client.resource.ClientTemplatesResource;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.GroupResource;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.IdentityProviderResource;
import org.keycloak.admin.client.resource.IdentityProvidersResource;
import org.keycloak.admin.client.resource.ProtocolMappersResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleByIdResource;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserFederationProviderResource;
import org.keycloak.admin.client.resource.UserFederationProvidersResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;

/**
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class AdminEventPaths {

    // REALM

    public static String deleteSessionPath(String userSessionId) {
        URI uri = UriBuilder.fromUri("").path(RealmResource.class, "deleteSession").build(userSessionId);
        return uri.toString();
    }


    // CLIENT RESOURCE

    public static String clientResourcePath(String clientDbId) {
        URI uri = UriBuilder.fromUri("").path(RealmResource.class, "clients").path(ClientsResource.class, "get").build(clientDbId);
        return uri.toString();
    }

    public static String clientRolesResourcePath(String clientDbId) {
        URI uri = UriBuilder.fromUri(clientResourcePath(clientDbId)).path(ClientResource.class, "roles").build();
        return uri.toString();
    }

    public static String clientRoleResourcePath(String clientDbId, String roleName) {
        URI uri = UriBuilder.fromUri(clientRolesResourcePath(clientDbId)).path(RolesResource.class, "get").build(roleName);
        return uri.toString();
    }

    public static String clientRoleResourceCompositesPath(String clientDbId, String roleName) {
        URI uri = UriBuilder.fromUri(clientRoleResourcePath(clientDbId, roleName))
                .path(RoleResource.class, "getRoleComposites").build();
        return uri.toString();
    }

    public static String clientProtocolMappersPath(String clientDbId) {
        URI uri = UriBuilder.fromUri(clientResourcePath(clientDbId))
                .path(ClientResource.class, "getProtocolMappers")
                .build();
        return uri.toString();
    }


    public static String clientProtocolMapperPath(String clientDbId, String protocolMapperId) {
        URI uri = UriBuilder.fromUri(clientProtocolMappersPath(clientDbId))
                .path(ProtocolMappersResource.class, "getMapperById")
                .build(protocolMapperId);
        return uri.toString();
    }

    public static String clientPushRevocationPath(String clientDbId) {
        URI uri = UriBuilder.fromUri(clientResourcePath(clientDbId)).path(ClientResource.class, "pushRevocation").build();
        return uri.toString();
    }

    public static String clientNodesPath(String clientDbId) {
        URI uri = UriBuilder.fromUri(clientResourcePath(clientDbId)).path(ClientResource.class, "registerNode").build();
        return uri.toString();
    }

    public static String clientNodePath(String clientDbId, String node) {
        URI uri = UriBuilder.fromUri(clientResourcePath(clientDbId)).path(ClientResource.class, "unregisterNode").build(node);
        return uri.toString();
    }

    public static String clientTestNodesAvailablePath(String clientDbId) {
        URI uri = UriBuilder.fromUri(clientResourcePath(clientDbId)).path(ClientResource.class, "testNodesAvailable").build();
        return uri.toString();
    }

    public static String clientGenerateSecretPath(String clientDbId) {
        URI uri = UriBuilder.fromUri(clientResourcePath(clientDbId)).path(ClientResource.class, "generateNewSecret").build();
        return uri.toString();
    }

    public static String clientRegenerateRegistrationAccessTokenPath(String clientDbId) {
        URI uri = UriBuilder.fromUri(clientResourcePath(clientDbId)).path(ClientResource.class, "regenerateRegistrationAccessToken").build();
        return uri.toString();
    }

    public static String clientCertificateGenerateSecretPath(String clientDbId, String certificateAttribute) {
        URI uri = UriBuilder.fromUri(clientResourcePath(clientDbId))
                .path(ClientResource.class, "getCertficateResource")
                .path(ClientAttributeCertificateResource.class, "generate")
                .build(certificateAttribute);
        return uri.toString();
    }


    public static String clientScopeMappingsRealmLevelPath(String clientDbId) {
        URI uri = UriBuilder.fromUri(clientResourcePath(clientDbId)).path(ClientResource.class, "getScopeMappings")
                .path(RoleMappingResource.class, "realmLevel")
                .build();
        return uri.toString();
    }

    public static String clientScopeMappingsClientLevelPath(String clientDbId, String clientOwningRoleId) {
        URI uri = UriBuilder.fromUri(clientResourcePath(clientDbId)).path(ClientResource.class, "getScopeMappings")
                .path(RoleMappingResource.class, "clientLevel")
                .build(clientOwningRoleId);
        return uri.toString();
    }



    // CLIENT TEMPLATES

    public static String clientTemplateResourcePath(String clientTemplateId) {
        URI uri = UriBuilder.fromUri("").path(RealmResource.class, "clientTemplates").path(ClientTemplatesResource.class, "get").build(clientTemplateId);
        return uri.toString();
    }

    public static String clientTemplateScopeMappingsRealmLevelPath(String clientTemplateDbId) {
        URI uri = UriBuilder.fromUri(clientTemplateResourcePath(clientTemplateDbId)).path(ClientTemplateResource.class, "getScopeMappings")
                .path(RoleMappingResource.class, "realmLevel")
                .build();
        return uri.toString();
    }

    public static String clientTemplateScopeMappingsClientLevelPath(String clientTemplateDbId, String clientOwningRoleId) {
        URI uri = UriBuilder.fromUri(clientTemplateResourcePath(clientTemplateDbId)).path(ClientTemplateResource.class, "getScopeMappings")
                .path(RoleMappingResource.class, "clientLevel")
                .build(clientOwningRoleId);
        return uri.toString();
    }

    public static String clientTemplateProtocolMappersPath(String clientTemplateDbId) {
        URI uri = UriBuilder.fromUri(clientTemplateResourcePath(clientTemplateDbId))
                .path(ClientTemplateResource.class, "getProtocolMappers")
                .build();
        return uri.toString();
    }


    public static String clientTemplateProtocolMapperPath(String clientTemplateDbId, String protocolMapperId) {
        URI uri = UriBuilder.fromUri(clientTemplateProtocolMappersPath(clientTemplateDbId))
                .path(ProtocolMappersResource.class, "getMapperById")
                .build(protocolMapperId);
        return uri.toString();
    }

    // ROLES

    public static String rolesResourcePath() {
        URI uri = UriBuilder.fromUri("").path(RealmResource.class, "roles").build();
        return uri.toString();
    }

    public static String roleResourcePath(String roleName) {
        URI uri = UriBuilder.fromUri(rolesResourcePath()).path(RolesResource.class, "get").build(roleName);
        return uri.toString();
    }

    public static String roleResourceCompositesPath(String roleName) {
        URI uri = UriBuilder.fromUri(roleResourcePath(roleName)).path(RoleResource.class, "getRoleComposites").build();
        return uri.toString();
    }

    public static String rolesByIdResourcePath() {
        URI uri = UriBuilder.fromUri("").path(RealmResource.class, "rolesById").build();
        return uri.toString();
    }

    public static String roleByIdResourcePath(String roleId) {
        URI uri = UriBuilder.fromUri(rolesByIdResourcePath()).path(RoleByIdResource.class, "getRole").build(roleId);
        return uri.toString();
    }

    public static String roleByIdResourceCompositesPath(String roleId) {
        URI uri = UriBuilder.fromUri(rolesByIdResourcePath()).path(RoleByIdResource.class, "getRoleComposites").build(roleId);
        return uri.toString();
    }

    // USERS

    public static String userResourcePath(String userId) {
        URI uri = UriBuilder.fromUri("").path(RealmResource.class, "users").path(UsersResource.class, "get").build(userId);
        return uri.toString();
    }

    public static String userResetPasswordPath(String userId) {
        URI uri = UriBuilder.fromUri(userResourcePath(userId)).path(UserResource.class, "resetPassword").build(userId);
        return uri.toString();
    }

    public static String userRealmRoleMappingsPath(String userId) {
        URI uri = UriBuilder.fromUri(userResourcePath(userId))
                .path(UserResource.class, "roles")
                .path(RoleMappingResource.class, "realmLevel").build();
        return uri.toString();
    }

    public static String userClientRoleMappingsPath(String userId, String clientDbId) {
        URI uri = UriBuilder.fromUri(userResourcePath(userId))
                .path(UserResource.class, "roles")
                .path(RoleMappingResource.class, "clientLevel").build(clientDbId);
        return uri.toString();
    }

    public static String userFederatedIdentityLink(String userId, String idpAlias) {
        URI uri = UriBuilder.fromUri(userResourcePath(userId))
                .path(UserResource.class, "addFederatedIdentity")
                .build(idpAlias);
        return uri.toString();
    }

    public static String userGroupPath(String userId, String groupId) {
        URI uri = UriBuilder.fromUri(userResourcePath(userId))
                .path(UserResource.class, "joinGroup")
                .build(groupId);
        return uri.toString();
    }

    // IDENTITY PROVIDERS

    public static String identityProvidersPath() {
        URI uri = UriBuilder.fromUri("").path(RealmResource.class, "identityProviders").build();
        return uri.toString();
    }

    public static String identityProviderCreatePath() {
        URI uri = UriBuilder.fromUri(identityProvidersPath()).path(IdentityProvidersResource.class, "create").build();
        return uri.toString();
    }

    public static String identityProviderPath(String idpAlias) {
        URI uri = UriBuilder.fromUri(identityProvidersPath()).path(IdentityProvidersResource.class, "get").build(idpAlias);
        return uri.toString();
    }

    public static String identityProviderMapperPath(String idpAlias, String idpMapperId) {
        URI uri = UriBuilder.fromUri(identityProviderPath(idpAlias)).path(IdentityProviderResource.class, "getMapperById").build(idpMapperId);
        return uri.toString();
    }

    // USER FEDERATION PROVIDERS AND MAPPERS

    public static String userFederationsResourcePath() {
        URI uri = UriBuilder.fromUri("").path(RealmResource.class, "userFederation").build();
        return uri.toString();
    }

    public static String userFederationCreateResourcePath() {
        URI uri = UriBuilder.fromUri(userFederationsResourcePath()).path(UserFederationProvidersResource.class, "create").build();
        return uri.toString();
    }

    public static String userFederationResourcePath(String userFederationId) {
        URI uri = UriBuilder.fromUri(userFederationsResourcePath()).path(UserFederationProvidersResource.class, "get").build(userFederationId);
        return uri.toString();
    }

    public static String userFederationMapperResourcePath(String userFederationId, String userFederationMapperId) {
        URI uri = UriBuilder.fromUri(userFederationResourcePath(userFederationId))
                .path(UserFederationProviderResource.class, "getMapperById").build(userFederationMapperId);
        return uri.toString();
    }

    // CLIENT INITIAL ACCESS

    public static String clientInitialAccessPath(String clientInitialAccessId) {
        URI uri = UriBuilder.fromUri("").path(RealmResource.class, "clientInitialAccess")
                .path(ClientInitialAccessResource.class, "delete")
                .build(clientInitialAccessId);
        return uri.toString();
    }

    // GROUPS

    public static String groupsPath() {
        URI uri = UriBuilder.fromUri("").path(RealmResource.class, "groups")
                .build();
        return uri.toString();
    }

    public static String groupPath(String groupId) {
        URI uri = UriBuilder.fromUri(groupsPath()).path(GroupsResource.class, "group")
                .build(groupId);
        return uri.toString();
    }

    public static String groupRolesPath(String groupId) {
        URI uri = UriBuilder.fromUri(groupPath(groupId))
                .path(GroupResource.class, "roles")
                .build();
        return uri.toString();
    }

    public static String groupRolesRealmRolesPath(String groupId) {
        URI uri = UriBuilder.fromUri(groupRolesPath(groupId))
                .path(RoleMappingResource.class, "realmLevel")
                .build();
        return uri.toString();
    }

    public static String groupRolesClientRolesPath(String groupId, String clientDbId) {
        URI uri = UriBuilder.fromUri(groupRolesPath(groupId))
                .path(RoleMappingResource.class, "clientLevel")
                .build(clientDbId);
        return uri.toString();
    }

    public static String groupSubgroupsPath(String groupId) {
        URI uri = UriBuilder.fromUri(groupPath(groupId))
                .path(GroupResource.class, "subGroup")
                .build();
        return uri.toString();
    }


}
