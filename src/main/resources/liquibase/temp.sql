    <changeSet author="hunter" id="create default user">
        <insert tableName="users">
            <column name="id" value="1"/>
            <column name="username" value="hunter"/>
            <column name="password" value="$2a$10$YBd.jCtCn7dh5Ps.n/c80efTzq5jUgepAvNV5DsuXnx.c0NaRnTJa"/>
            <column name="email" value="hunter9000@gmail.com" />
        </insert>

        <insert tableName="role">
            <column name="id" value="1"/>
            <column name="role_name" value="PLAYER"/>
        </insert>
        <insert tableName="role">
            <column name="id" value="2"/>
            <column name="role_name" value="ADMIN"/>
        </insert>

        <insert tableName="user_role">
            <column name="user_id" value="1"/>
            <column name="role_id" value="1"/>
        </insert>
        <insert tableName="user_role">
            <column name="user_id" value="1"/>
            <column name="role_id" value="2"/>
        </insert>

    </changeSet>