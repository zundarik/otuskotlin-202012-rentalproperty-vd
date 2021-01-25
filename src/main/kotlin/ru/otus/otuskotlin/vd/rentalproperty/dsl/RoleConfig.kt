package ru.otus.otuskotlin.vd.rentalproperty.dsl

import ru.otus.otuskotlin.vd.rentalproperty.enums.PrivilegeEnum
import ru.otus.otuskotlin.vd.rentalproperty.enums.RoleEnum
import ru.otus.otuskotlin.vd.rentalproperty.model.Role

@UserDSL
class RoleConfig {
  private var name: String = RoleEnum.USER.name
  private var privileges: MutableSet<PrivilegeEnum> = mutableSetOf()
  private val _roles: MutableSet<Role> = mutableSetOf()
  val roles: Set<Role>
    get() = _roles.toSet()

  fun add(role: Role) = _roles.add(role)

  fun build() = Role(
    name = name,
    privileges = privileges.toMutableSet()
  )

  fun name(role: RoleEnum) {
    name = role.name
  }

  fun name(block: RoleNameConf.() -> Unit) {
    val roleNameConf = RoleNameConf().apply(block)
    name = roleNameConf.toString()
  }

  fun privileges(block: PrivilegeConf.() -> Unit) {
    val privilegeConf = PrivilegeConf().apply(block)
    privileges = privilegeConf.privileges.toMutableSet()
  }
}

fun role(block: RoleConfig.() -> Unit) = RoleConfig().apply(block).build()